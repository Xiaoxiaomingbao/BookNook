package com.booknook.mapper;

import com.booknook.domain.po.Cart;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

@Mapper
public interface CartMapper {

    @Insert("INSERT INTO cart (user_id, item_id, num, name, price, image, create_time, update_time) " +
            "VALUES (#{userId}, #{itemId}, #{num}, #{name}, #{price}, #{image}, #{createTime}, #{updateTime})")
    int insert(Cart cart);

    @Select("SELECT * FROM cart WHERE user_id = #{userId}")
    List<Cart> selectByUserId(Long userId);

    @Update("UPDATE cart SET num = num + #{num} WHERE user_id = #{userId} AND item_id = #{itemId}")
    void updateNum(@Param("itemId") Long itemId, @Param("num") int num, @Param("userId") Long userId);

    void deleteByItemIds(@Param("userId") Long userId, @Param("itemIds") Collection<Long> itemIds);

    @Select("SELECT COUNT(*) FROM cart WHERE user_id = #{userId}")
    int countByUserId(Long userId);

    @Select("SELECT COUNT(*) FROM cart WHERE user_id = #{userId} AND item_id = #{itemId}")
    int countByUserIdAndItemId(@Param("userId") Long userId, @Param("itemId") Long itemId);

    @Update("UPDATE cart SET num = #{num}, name = #{name}, price = #{price}, image = #{image}, update_time = #{updateTime} WHERE id = #{id} AND user_id = #{userId}")
    void update(Cart cart);
}
