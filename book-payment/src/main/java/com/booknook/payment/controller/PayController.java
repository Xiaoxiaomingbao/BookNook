package com.booknook.payment.controller;



import com.booknook.common.domain.dto.PayApplyDTO;
import com.booknook.common.domain.dto.PayOrderDTO;

import com.booknook.common.domain.dto.PayOrderFormDTO;
import com.booknook.common.domain.vo.PayOrderVO;
import com.booknook.common.exception.BizIllegalException;
import com.booknook.common.utils.BeanUtils;


import com.booknook.payment.domain.po.PayOrder;


import com.booknook.payment.enums.PayType;

import com.booknook.payment.service.IPayOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "支付相关接口")
@RestController
@RequestMapping("pay-orders")
@RequiredArgsConstructor
public class PayController {

    private final IPayOrderService payOrderService;

    @ApiOperation("查询支付单")
    @GetMapping
    public List<PayOrderVO> queryPayOrders(){
        return BeanUtils.copyList(payOrderService.list(), PayOrderVO.class);
    }

    @ApiOperation("生成支付单")
    @PostMapping
    public String applyPayOrder(@RequestBody PayApplyDTO applyDTO){
        if(!PayType.BALANCE.equalsValue(applyDTO.getPayType())){
            // 目前只支持余额支付
            throw new BizIllegalException("抱歉，目前只支持余额支付");
        }
        return payOrderService.applyPayOrder(applyDTO);
    }

    @ApiOperation("尝试基于用户余额支付")
    @ApiImplicitParam(value = "支付单id", name = "id")
    @PostMapping("{id}")
    public void tryPayOrderByBalance(@PathVariable("id") Long id, @RequestBody PayOrderFormDTO payOrderFormDTO){
        payOrderFormDTO.setId(id);
        payOrderService.tryPayOrderByBalance(payOrderFormDTO);
    }

    @ApiOperation("根据id查询支付单")
    @GetMapping("/biz/{id}")
    public PayOrderDTO queryPayOrderByBizOrderNo(@PathVariable("id") Long id){
        // 替换MyBatis Plus的lambdaQuery()方法
        PayOrder payOrder = payOrderService.queryByBizOrderNo(id);
        return BeanUtils.copyBean(payOrder, PayOrderDTO.class);
    }
}