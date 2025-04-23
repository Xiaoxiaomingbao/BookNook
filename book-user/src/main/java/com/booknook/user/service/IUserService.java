package com.booknook.user.service;

import com.booknook.user.domain.dto.LoginFormDTO;
import com.booknook.user.domain.po.User;
import com.booknook.user.domain.vo.UserLoginVO;

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
     * 根据用户名查询用户
     */
    User getUserByUsername(String username);

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

    /**
     * 登录
     * @param loginFormDTO 登录表单
     * @return 登录成功后获得的用户信息
     */
    UserLoginVO login(LoginFormDTO loginFormDTO);
}
