package com.booknook.mapper;


import com.booknook.domain.po.OrderLogistics;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 订单物流信息 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface OrderLogisticsMapper {

    @Insert("INSERT INTO order_logistics (order_id, logistics_number, logistics_company, contact, mobile, province, city, town, street, create_time, update_time) " +
            "VALUES (#{orderId}, #{logisticsNumber}, #{logisticsCompany}, #{contact}, #{mobile}, #{province}, #{city}, #{town}, #{street}, #{createTime}, #{updateTime})")
    int insertOrderLogistics(OrderLogistics orderLogistics);

    @Select("SELECT * FROM order_logistics WHERE order_id = #{orderId}")
    OrderLogistics selectOrderLogisticsByOrderId(Long orderId);

    @Update("UPDATE order_logistics SET logistics_number = #{logisticsNumber}, logistics_company = #{logisticsCompany}, contact = #{contact}, " +
            "mobile = #{mobile}, province = #{province}, city = #{city}, town = #{town}, street = #{street}, update_time = #{updateTime} " +
            "WHERE order_id = #{orderId}")
    int updateOrderLogistics(OrderLogistics orderLogistics);

    @Delete("DELETE FROM order_logistics WHERE order_id = #{orderId}")
    int deleteOrderLogistics(Long orderId);
}
