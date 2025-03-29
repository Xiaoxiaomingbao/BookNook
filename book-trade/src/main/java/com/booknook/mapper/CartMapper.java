package com.booknook.mapper;


import com.booknook.domain.po.Cart;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

@Mapper
public interface CartMapper {

    @Insert("INSERT INTO cart (user_id, item_id, num, name, spec, price, image, create_time, update_time) " +
            "VALUES (#{userId}, #{itemId}, #{num}, #{name}, #{spec}, #{price}, #{image}, #{createTime}, #{updateTime})")
    int insert(Cart cart);

    @Select("SELECT * FROM cart WHERE user_id = #{userId}")
    List<Cart> selectByUserId(Long userId);

    @Update("UPDATE cart SET num = num + 1 WHERE user_id = #{userId} AND item_id = #{itemId}")
    void updateNum(Long itemId, Long userId);

    @Delete("DELETE FROM cart WHERE user_id = #{userId} AND item_id IN (#{itemIds})")
    void deleteByItemIds(@Param("userId") Long userId, @Param("itemIds") Collection<Long> itemIds);

    @Select("SELECT COUNT(*) FROM cart WHERE user_id = #{userId}")
    int countByUserId(Long userId);

    @Select("SELECT COUNT(*) FROM cart WHERE user_id = #{userId} AND item_id = #{itemId}")
    int countByUserIdAndItemId(Long userId, Long itemId);

    @Update("UPDATE cart SET num = #{num}, name = #{name}, spec = #{spec}, price = #{price}, image = #{image}, update_time = #{updateTime} WHERE id = #{id} AND user_id = #{userId}")
    void update(Cart cart);
}
