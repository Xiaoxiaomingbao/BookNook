package com.booknook.user.service.impl;

import com.booknook.common.exception.BizIllegalException;
import com.booknook.common.utils.UserContext;
import com.booknook.user.domain.po.User;
import com.booknook.user.mapper.UserMapper;
import com.booknook.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    /*PasswordEncoder passwordEncoder;*/

    @Override
    public List<User> listUsers() {
        return Collections.emptyList();
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public void deductMoney(String pw, Integer totalFee) {
        log.info("开始扣款");
        // 1. 校验密码
        Long currentUserId = 1L;
        /*User user = userMapper.selectUserById(currentUserId);
        if (user == null || !passwordEncoder.matches(pw, user.getPassword())) {
            // 密码错误
            throw new BizIllegalException("用户密码错误");
        }*/

        // 2. 尝试扣款
        try {
            userMapper.updateMoney(currentUserId, totalFee);
        } catch (Exception e) {
            throw new RuntimeException("扣款失败，可能是余额不足！", e);
        }
        log.info("扣款成功");
    }
}