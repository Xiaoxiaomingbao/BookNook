package com.booknook.service.impl;

import com.booknook.common.client.CartClient;
import com.booknook.common.client.ProductClient;
import com.booknook.common.domain.dto.OrderDetailDTO;
import com.booknook.common.domain.dto.OrderFormDTO;
import com.booknook.common.domain.dto.ProductDTO;
import com.booknook.common.exception.BadRequestException;
import com.booknook.common.utils.UserContext;
import com.booknook.domain.po.Order;
import com.booknook.domain.po.OrderDetail;
import com.booknook.mapper.OrderMapper;
import com.booknook.service.IOrderDetailService;
import com.booknook.service.IOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final ProductClient itemClient;
    private final IOrderDetailService detailService;
    private final CartClient cartClient;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public Long createOrder(OrderFormDTO orderFormDTO) {
        // 1.订单数据
        Order order = new Order();
        // 1.1.查询商品
        List<OrderDetailDTO> detailDTOS = orderFormDTO.getDetails();
        // 1.2.获取商品id和数量的Map
        Map<Long, Integer> itemNumMap = detailDTOS.stream()
                .collect(Collectors.toMap(OrderDetailDTO::getPid, OrderDetailDTO::getNum));
        Set<Long> itemIds = itemNumMap.keySet();
        // 1.3.查询商品
        List<ProductDTO> items = itemClient.queryItemByIds(itemIds);
        if (items == null || items.size() < itemIds.size()) {
            throw new BadRequestException("商品不存在");
        }
        log.warn("查询商品错误");
        // 1.4.基于商品价格、购买数量计算商品总价：totalFee
        int total = 0;
        for (ProductDTO item : items) {
            total += item.getPrice() * itemNumMap.get(item.getPid());
        }
        order.setTotalFee(total);
        log.warn("计算总价错误");
        // 1.5.其它属性
        order.setPaymentType(orderFormDTO.getPaymentType());
        order.setUserId(UserContext.getUser());
        order.setStatus(1);
        // 1.6.将Order写入数据库order表中
        orderMapper.insertOrder(order);
        log.warn("将Order写入数据库order表中错误");
        // 2.保存订单详情
        List<OrderDetail> details = buildDetails(order.getId(), items, itemNumMap);
        // 替换批量保存方法，改为循环保存每个订单详情
        for (OrderDetail detail : details) {
            detailService.save(detail);
        }

        // 3.清理购物车商品
        cartClient.deleteCartItemByIds(itemIds);
        log.warn("清理购物车商品错误");

        // 4.扣减库存
        try {
            itemClient.deductStock(detailDTOS);
        } catch (Exception e) {
            throw new RuntimeException("库存不足！");
        }

        // TODO 5.发送延迟消息，检测订单支付状态

        return order.getId();
    }

    @Override
    public void markOrderPaySuccess(Long orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(2);
        order.setPayTime(LocalDateTime.now());
        orderMapper.updateOrder(order);
    }

    @Override
    public void cancelOrder(Long orderId) {
        // 标记订单为已关闭
        orderMapper.updateOrderStatusToCancelled(orderId);
        // TODO 恢复库存
    }

    @Override
    public Order getById(Long orderId) {
        return orderMapper.selectOrderById(orderId);
    }

    @Override
    public List<Order> getAll() {
        return orderMapper.selectAllOrders();
    }

    @Override
    public void save(Order order) {
        orderMapper.insertOrder(order);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateOrder(order);
    }

    @Override
    public void delete(Long orderId) {
        orderMapper.deleteOrder(orderId);
    }

    private List<OrderDetail> buildDetails(Long orderId, List<ProductDTO> items, Map<Long, Integer> numMap) {
        List<OrderDetail> details = new ArrayList<>(items.size());
        for (ProductDTO item : items) {
            OrderDetail detail = new OrderDetail();
            detail.setName(item.getName());
            detail.setPrice(item.getPrice());
            detail.setNum(numMap.get(item.getPid()));
            detail.setItemId(item.getPid());
            detail.setOrderId(orderId);
            details.add(detail);
        }
        return details;
    }
}