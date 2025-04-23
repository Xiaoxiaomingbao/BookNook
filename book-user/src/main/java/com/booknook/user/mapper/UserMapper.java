package com.booknook.user.mapper;

import com.booknook.user.domain.po.UserRawPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询单个用户
     */
    @Select("SELECT id, username, password, phone, create_time, update_time, status, balance FROM user WHERE id = #{id}")
    UserRawPO selectUserById(Long id);

    /**
     * 根据用户名查询用户
     */
    @Select("SELECT id, username, password, phone, create_time, update_time, status, balance FROM user WHERE username = #{username}")
    List<UserRawPO> selectUserByUsername(String username);

    /**
     * 查询所有用户
     */
    @Select("SELECT * FROM user")
    List<UserRawPO> selectAllUsers();

    /**
     * 插入用户
     */
    @Insert("INSERT INTO user (username, password, phone, create_time, update_time, status, balance) " +
            "VALUES (#{username}, #{password}, #{phone}, #{createTime}, #{updateTime}, #{status}, #{balance})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(UserRawPO user);

    /**
     * 更新用户
     */
    @Update("UPDATE user SET username = #{username}, password = #{password}, phone = #{phone}, " +
            "create_time = #{createTime}, update_time = #{updateTime}, status = #{status}, balance = #{balance} WHERE id = #{id}")
    int updateUser(UserRawPO user);

    /**
     * 删除用户
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUserById(Long id);

    /**
     * 更新用户余额
     */
    @Update("UPDATE user SET balance = balance - #{totalFee} WHERE id = #{userId}")
    void updateMoney(@Param("userId") Long userId, @Param("totalFee") Integer totalFee);
}
