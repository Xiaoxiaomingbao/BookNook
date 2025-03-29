package com.booknook.service;

import com.booknook.common.domain.dto.OrderFormDTO;
import com.booknook.domain.po.Order;

import java.util.List;

public interface IOrderService {

    /**
     * 创建订单
     *
     * @param orderFormDTO 订单表单DTO
     * @return 订单ID
     */
    Long createOrder(OrderFormDTO orderFormDTO);

    /**
     * 标记订单支付成功
     *
     * @param orderId 订单ID
     */
    void markOrderPaySuccess(Long orderId);

    /**
     * 取消订单
     *
     * @param orderId 订单ID
     */
    void cancelOrder(Long orderId);

    /**
     * 根据订单ID查询订单
     *
     * @param orderId 订单ID
     * @return 订单对象
     */
    Order getById(Long orderId);

    /**
     * 获取所有订单
     *
     * @return 订单列表
     */
    List<Order> getAll();

    /**
     * 保存订单
     *
     * @param order 订单对象
     */
    void save(Order order);

    /**
     * 更新订单
     *
     * @param order 订单对象
     */
    void update(Order order);

    /**
     * 删除订单
     *
     * @param orderId 订单ID
     */
    void delete(Long orderId);
}

