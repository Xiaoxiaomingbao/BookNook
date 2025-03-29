package com.booknook.mapper;

import com.booknook.domain.po.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 订单详情表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface OrderDetailMapper {

    @Insert("INSERT INTO order_detail (order_id, item_id, num, name, spec, price, image, create_time, update_time) " +
            "VALUES (#{orderId}, #{itemId}, #{num}, #{name}, #{spec}, #{price}, #{image}, #{createTime}, #{updateTime})")
    int insertOrderDetail(OrderDetail orderDetail);

    @Select("SELECT * FROM order_detail WHERE id = #{id}")
    OrderDetail selectOrderDetailById(Long id);

    @Select("SELECT * FROM order_detail WHERE order_id = #{orderId}")
    List<OrderDetail> selectOrderDetailsByOrderId(Long orderId);

    @Update("UPDATE order_detail SET order_id = #{orderId}, item_id = #{itemId}, num = #{num}, name = #{name}, " +
            "spec = #{spec}, price = #{price}, image = #{image}, create_time = #{createTime}, update_time = #{updateTime} " +
            "WHERE id = #{id}")
    int updateOrderDetail(OrderDetail orderDetail);

    @Delete("DELETE FROM order_detail WHERE id = #{id}")
    int deleteOrderDetail(Long id);
}
