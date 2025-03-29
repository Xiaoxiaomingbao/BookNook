package com.booknook.service;



import com.booknook.domain.po.OrderDetail;

import java.util.List;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface IOrderDetailService {

    /**
     * 保存订单详情
     * @param orderDetail 订单详情
     */
    void save(OrderDetail orderDetail);

    /**
     * 根据ID查询订单详情
     * @param id 订单详情ID
     * @return 订单详情
     */
    OrderDetail getById(Long id);

    /**
     * 根据订单ID查询订单详情列表
     * @param orderId 订单ID
     * @return 订单详情列表
     */
    List<OrderDetail> getByOrderId(Long orderId);

    /**
     * 更新订单详情
     * @param orderDetail 订单详情
     */
    void update(OrderDetail orderDetail);

    /**
     * 删除订单详情
     * @param id 订单详情ID
     */
    void delete(Long id);
}