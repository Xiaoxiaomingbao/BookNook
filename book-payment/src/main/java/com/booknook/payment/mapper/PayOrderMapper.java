package com.booknook.payment.mapper;


import com.booknook.payment.domain.po.PayOrder;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 支付订单 Mapper 接口
 * </p>
 *
 */

@Mapper
public interface PayOrderMapper {

    // 根据ID查询支付订单
    PayOrder findById(Long id);

    // 通过业务订单号查询支付订单
    PayOrder findByBizOrderNo(Long bizOrderNo);

    // 新增支付订单，返回影响的行数
    int insert(PayOrder payOrder);

    // 更新支付订单，返回影响的行数
    int update(PayOrder payOrder);

    // 查询所有支付订单
    List<PayOrder> findAll();

    // 根据ID删除支付订单，返回影响的行数
    int deleteById(Long id);
}