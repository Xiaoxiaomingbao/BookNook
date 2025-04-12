package com.booknook.payment.service;


import com.booknook.common.domain.dto.PayApplyDTO;
import com.booknook.common.domain.dto.PayOrderFormDTO;
import com.booknook.payment.domain.po.PayOrder;

import java.util.List;

/**
 * <p>
 * 支付订单 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-16
 */
public interface IPayOrderService {
    /**
     * 申请支付订单
     * @param applyDTO 支付申请DTO
     * @return 支付订单ID
     */
    String applyPayOrder(PayApplyDTO applyDTO);

    /**
     * 尝试使用余额支付订单
     * @param payOrderFormDTO 支付表单DTO
     */
    void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO);

    /**
     * 根据ID查询支付订单
     * @param id 支付订单ID
     * @return 支付订单
     */
    PayOrder getById(Long id);

    /**
     * 查询所有支付订单
     * @return 支付订单列表
     */
    List<PayOrder> list();

    /**
     * 保存支付订单
     * @param payOrder 支付订单
     * @return 是否成功
     */
    boolean save(PayOrder payOrder);

    /**
     * 根据ID更新支付订单
     * @param payOrder 支付订单
     * @return 是否成功
     */
    boolean updateById(PayOrder payOrder);

    /**
     * 根据ID删除支付订单
     * @param id 支付订单ID
     * @return 是否成功
     */
    boolean removeById(Long id);

    /**
     * 根据业务订单号查询支付订单
     * @param bizOrderNo 业务订单号
     * @return 支付订单
     */
    PayOrder queryByBizOrderNo(Long bizOrderNo);

    /**
     * 标记支付订单为支付成功
     * @param id 支付订单ID
     * @param successTime 支付成功时间
     * @return 是否成功
     */
    boolean markPayOrderSuccess(Long id, java.time.LocalDateTime successTime);
}