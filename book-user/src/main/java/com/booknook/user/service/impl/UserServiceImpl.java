package com.booknook.user.service.impl;

import com.booknook.common.exception.BadRequestException;
import com.booknook.common.exception.BizIllegalException;
import com.booknook.common.exception.DbException;
import com.booknook.common.exception.ForbiddenException;
import com.booknook.common.utils.UserContext;
import com.booknook.user.config.JwtProperties;
import com.booknook.user.domain.dto.LoginFormDTO;
import com.booknook.user.domain.po.User;
import com.booknook.user.domain.po.UserRawPO;
import com.booknook.user.domain.vo.UserLoginVO;
import com.booknook.user.enums.UserStatus;
import com.booknook.user.mapper.UserMapper;
import com.booknook.user.service.IUserService;
import com.booknook.user.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtTool jwtTool;

    private final JwtProperties jwtProperties;

    @Override
    public List<User> listUsers() {
        return userMapper.selectAllUsers().stream()
                .map(raw -> new User(raw.getId(), raw.getUsername(), raw.getPassword(), raw.getPhone(),
                        raw.getCreateTime(), raw.getUpdateTime(), UserStatus.of(raw.getStatus()), raw.getBalance()))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        UserRawPO raw = userMapper.selectUserById(id);
        return new User(raw.getId(), raw.getUsername(), raw.getPassword(), raw.getPhone(),
                raw.getCreateTime(), raw.getUpdateTime(), UserStatus.of(raw.getStatus()), raw.getBalance());
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> users = userMapper.selectUserByUsername(username).stream()
                .map(raw -> new User(raw.getId(), raw.getUsername(), raw.getPassword(), raw.getPhone(),
                        raw.getCreateTime(), raw.getUpdateTime(), UserStatus.of(raw.getStatus()), raw.getBalance()))
                .collect(Collectors.toList());
        if (users.isEmpty()) {
            throw new DbException("找不到用户名！");
        }
        if (users.size() > 1) {
            throw new DbException("出现重复用户名！");
        }
        return users.get(0);
    }

    @Override
    public void addUser(User user) {
        UserRawPO raw = new UserRawPO(user.getId(), user.getUsername(), user.getPassword(), user.getPhone(),
                user.getCreateTime(), user.getUpdateTime(), user.getStatus().getValue(), user.getBalance());
        int linesChanged = userMapper.insertUser(raw);
        if (linesChanged != 1) {
            throw new DbException("新增用户失败！");
        }
    }

    @Override
    public void updateUser(User user) {
        UserRawPO raw = new UserRawPO(user.getId(), user.getUsername(), user.getPassword(), user.getPhone(),
                user.getCreateTime(), user.getUpdateTime(), user.getStatus().getValue(), user.getBalance());
        int linesChanged = userMapper.updateUser(raw);
        if (linesChanged != 1) {
            throw new DbException("更新用户失败！");
        }
    }

    @Override
    public void deleteUserById(Long id) {
        int linesChanged = userMapper.deleteUserById(id);
        if (linesChanged != 1) {
            throw new DbException("删除用户失败！");
        }
    }

    @Override
    public UserLoginVO login(LoginFormDTO loginDTO) {
        // 1.数据校验
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        // 2.根据用户名或手机号查询
        User user = getUserByUsername(username);
        Assert.notNull(user, "用户名错误");
        // 3.校验是否禁用
        if (user.getStatus() == UserStatus.FROZEN) {
            throw new ForbiddenException("用户被冻结");
        }
        // 4.校验密码
        /*String encoded = passwordEncoder.encode(password);
        log.info("编码后的密码: {}", encoded);
        log.info(String.valueOf(passwordEncoder.matches(password, encoded)));*/
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("用户名或密码错误");
        }
        // 5.生成TOKEN
        String token = jwtTool.createToken(user.getId(), jwtProperties.getTokenTTL());
        // 6.封装VO返回
        UserLoginVO vo = new UserLoginVO();
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setBalance(user.getBalance());
        vo.setToken(token);
        return vo;
    }

    @Override
    public void deductMoney(String pw, Integer totalFee) {
        log.info("开始扣款");
        // 1. 校验密码
        User user = getUserById(UserContext.getUser());
        if(user == null || !passwordEncoder.matches(pw, user.getPassword())){
            // 密码错误
            throw new BizIllegalException("用户密码错误");
        }
        // 2. 尝试扣款
        try {
            userMapper.updateMoney(UserContext.getUser(), totalFee);
        } catch (Exception e) {
            throw new RuntimeException("扣款失败，可能是余额不足！", e);
        }
        log.info("扣款成功");
    }
}