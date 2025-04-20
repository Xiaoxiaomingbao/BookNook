package com.booknook.payment.service.impl;


import com.booknook.common.client.UserClient;
import com.booknook.common.domain.dto.PayApplyDTO;
import com.booknook.common.domain.dto.PayOrderFormDTO;
import com.booknook.payment.domain.po.PayOrder;
import com.booknook.payment.mapper.PayOrderMapper;
import com.booknook.payment.service.IPayOrderService;

import com.booknook.common.exception.BizIllegalException;
import com.booknook.common.utils.BeanUtils;
import com.booknook.common.utils.UserContext;


import com.booknook.payment.enums.PayStatus;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 支付订单 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PayOrderServiceImpl implements IPayOrderService {

    private final PayOrderMapper payOrderMapper;
    private final UserClient userClient;
    // private final TradeClient tradeClient;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public List<PayOrder> list() {
        return payOrderMapper.findAll();
    }

    @Override
    public String applyPayOrder(PayApplyDTO applyDTO) {
        // 1.幂等性校验
        PayOrder payOrder = checkIdempotent(applyDTO);
        // 2.返回结果
        return payOrder.getId().toString();
    }

    @Override
    public boolean removeById(Long id) {
        return payOrderMapper.deleteById(id) > 0;
    }

    @Override
    @Transactional
    public void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO) {
        // 1.查询支付单
        PayOrder po = getById(payOrderFormDTO.getId());
        // 2.判断状态
        if(!PayStatus.WAIT_BUYER_PAY.equalsValue(po.getStatus())){
            // 订单不是未支付，状态异常
            throw new BizIllegalException("交易已支付或关闭！");
        }
        // 3.尝试扣减余额
        userClient.deductMoney(payOrderFormDTO.getPw(), po.getAmount());
        // 4.修改支付单状态
        boolean success = markPayOrderSuccess(payOrderFormDTO.getId(), LocalDateTime.now());
        if (!success) {
            throw new BizIllegalException("交易已支付或关闭！");
        }
        // TODO 5.修改订单状态
        // tradeClient.markOrderPaySuccess(po.getBizOrderNo());
        /*try {
            rabbitTemplate.convertAndSend("pay.direct", "pay.success", po.getBizOrderNo());
        }catch (Exception e){
            log.error("发送支付状态通知失败，订单id：{}", po.getBizOrderNo(), e);
        }*/
    }

    public boolean markPayOrderSuccess(Long id, LocalDateTime successTime) {
        // 查询当前支付单
        PayOrder payOrder = getById(id);
        // 判断支付状态是否符合条件
        if (payOrder == null) {
            return false;
        }

        // 检查当前状态是否为NOT_COMMIT或WAIT_BUYER_PAY
        int status = payOrder.getStatus();
        if (status != PayStatus.NOT_COMMIT.getValue() &&
                status != PayStatus.WAIT_BUYER_PAY.getValue()) {
            return false;
        }
        // 更新支付单状态
        payOrder.setStatus(PayStatus.TRADE_SUCCESS.getValue());
        payOrder.setPaySuccessTime(successTime);
        return updateById(payOrder);
    }

    private PayOrder checkIdempotent(PayApplyDTO applyDTO) {
        // 1.首先查询支付单
        PayOrder oldOrder = queryByBizOrderNo(applyDTO.getBizOrderNo());
        // 2.判断是否存在
        if (oldOrder == null) {
            // 不存在支付单，说明是第一次，写入新的支付单并返回
            PayOrder payOrder = buildPayOrder(applyDTO);
            payOrder.setPayOrderNo(generateId());
            save(payOrder);
            return payOrder;
        }
        // 3.旧单已经存在，判断是否支付成功
        if (PayStatus.TRADE_SUCCESS.equalsValue(oldOrder.getStatus())) {
            // 已经支付成功，抛出异常
            throw new BizIllegalException("订单已经支付！");
        }
        // 4.旧单已经存在，判断是否已经关闭
        if (PayStatus.TRADE_CLOSED.equalsValue(oldOrder.getStatus())) {
            // 已经关闭，抛出异常
            throw new BizIllegalException("订单已关闭");
        }
        // 5.旧单已经存在，判断支付渠道是否一致
        if (!Objects.equals(oldOrder.getPayChannelCode(), applyDTO.getPayChannelCode())) {
            // 支付渠道不一致，需要重置数据，然后重新申请支付单
            PayOrder payOrder = buildPayOrder(applyDTO);
            payOrder.setId(oldOrder.getId());
            payOrder.setQrCodeUrl("");
            updateById(payOrder);
            payOrder.setPayOrderNo(oldOrder.getPayOrderNo());
            return payOrder;
        }
        // 6.旧单已经存在，且可能是未支付或未提交，且支付渠道一致，直接返回旧数据
        return oldOrder;
    }

    private PayOrder buildPayOrder(PayApplyDTO payApplyDTO) {
        // 1.数据转换
        PayOrder payOrder = BeanUtils.toBean(payApplyDTO, PayOrder.class);
        // 2.初始化数据
        payOrder.setPayOverTime(LocalDateTime.now().plusMinutes(120L));
        payOrder.setStatus(PayStatus.WAIT_BUYER_PAY.getValue());
        payOrder.setBizUserId(UserContext.getUser());
        return payOrder;
    }

    public PayOrder queryByBizOrderNo(Long bizOrderNo) {
        // 实现通过业务订单号查询支付单的方法
        // 这里需要调用mapper层方法
        return payOrderMapper.findByBizOrderNo(bizOrderNo);
    }

    // 添加基础CRUD方法的实现
    public PayOrder getById(Long id) {
        return payOrderMapper.findById(id);
    }

    public boolean save(PayOrder payOrder) {
        return payOrderMapper.insert(payOrder) > 0;
    }

    public boolean updateById(PayOrder payOrder) {
        return payOrderMapper.update(payOrder) > 0;
    }

    // 生成唯一ID的方法，替代IdWorker.getId()
    private Long generateId() {
        // 简单实现，实际可以使用雪花算法等方式生成唯一ID
        return System.currentTimeMillis() + (long)(Math.random() * 1000);
    }
}