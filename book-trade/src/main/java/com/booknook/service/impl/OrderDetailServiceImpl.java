package com.booknook.service.impl;


import com.booknook.domain.po.OrderDetail;
import com.booknook.mapper.OrderDetailMapper;
import com.booknook.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements IOrderDetailService {

    private final OrderDetailMapper orderDetailMapper;

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailMapper.insertOrderDetail(orderDetail);
    }

    @Override
    public OrderDetail getById(Long id) {
        return orderDetailMapper.selectOrderDetailById(id);
    }

    @Override
    public List<OrderDetail> getByOrderId(Long orderId) {
        return orderDetailMapper.selectOrderDetailsByOrderId(orderId);
    }

    @Override
    public void update(OrderDetail orderDetail) {
        orderDetailMapper.updateOrderDetail(orderDetail);
    }

    @Override
    public void delete(Long id) {
        orderDetailMapper.deleteOrderDetail(id);
    }
}