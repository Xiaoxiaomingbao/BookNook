package com.booknook.user.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserRawPO {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private int status;

    private Integer balance;
}
