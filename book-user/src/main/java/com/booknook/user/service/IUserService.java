package com.booknook.user.service;

import com.booknook.user.domain.po.User;

import java.util.List;

public interface IUserService {

    /**
     * 查询用户列表
     */
    List<User> listUsers();

    /**
     * 根据ID查询用户
     */
    User getUserById(Long id);

    /**
     * 添加用户
     */
    void addUser(User user);

    /**
     * 更新用户
     */
    void updateUser(User user);

    /**
     * 删除用户
     */
    void deleteUserById(Long id);

    /**
     * 扣除用户金额
     */
    void deductMoney(String pw, Integer totalFee);
}
