package com.booknook.mapper;

import com.booknook.domain.po.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
@Mapper
public interface OrderMapper {

    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert("INSERT INTO `order` (total_fee, payment_type, user_id, status, create_time, pay_time, consign_time, end_time, close_time, comment_time, update_time) " +
            "VALUES (#{totalFee}, #{paymentType}, #{userId}, #{status}, #{createTime}, #{payTime}, #{consignTime}, #{endTime}, #{closeTime}, #{commentTime}, #{updateTime})")
    int insertOrder(Order order);

    @Select("SELECT * FROM `order` WHERE id = #{id}")
    Order selectOrderById(Long id);

    @Select("SELECT * FROM `order`")
    List<Order> selectAllOrders();

    @Update("UPDATE `order` SET total_fee = #{totalFee}, payment_type = #{paymentType}, user_id = #{userId}, status = #{status}, " +
            "create_time = #{createTime}, pay_time = #{payTime}, consign_time = #{consignTime}, end_time = #{endTime}, " +
            "close_time = #{closeTime}, comment_time = #{commentTime}, update_time = #{updateTime} WHERE id = #{id}")
    int updateOrder(Order order);

    @Delete("DELETE FROM `order` WHERE id = #{id}")
    int deleteOrder(Long id);

    @Update("UPDATE `order` SET status = 2, update_time = NOW() WHERE id = #{orderId}")
    void updateOrderStatusToPaid(Long orderId);

    @Update("UPDATE `order` SET status = 5, update_time = NOW() WHERE id = #{orderId}")
    void updateOrderStatusToCancelled(Long orderId);

}
