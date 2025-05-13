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

    @Insert("INSERT INTO detail (order_id, pid, num, name, price, create_time, update_time) " +
            "VALUES (#{orderId}, #{itemId}, #{num}, #{name}, #{price}, #{createTime}, #{updateTime})")
    int insertOrderDetail(OrderDetail orderDetail);

    @Select("SELECT * FROM detail WHERE id = #{id}")
    OrderDetail selectOrderDetailById(Long id);

    @Select("SELECT * FROM detail WHERE order_id = #{orderId}")
    List<OrderDetail> selectOrderDetailsByOrderId(Long orderId);

    @Update("UPDATE detail SET order_id = #{orderId}, pid = #{itemId}, num = #{num}, name = #{name}, " +
            "price = #{price}, create_time = #{createTime}, update_time = #{updateTime} " +
            "WHERE id = #{id}")
    int updateOrderDetail(OrderDetail orderDetail);

    @Delete("DELETE FROM detail WHERE id = #{id}")
    int deleteOrderDetail(Long id);
}
