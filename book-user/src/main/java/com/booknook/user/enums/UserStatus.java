package com.booknook.user.enums;


import com.booknook.common.exception.BadRequestException;
import lombok.Getter;


@Getter
public enum UserStatus {
    FROZEN(0, "禁止使用"),
    NORMAL(1, "已激活");

    @Getter
    private int value;
    private String desc;

    UserStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserStatus of(int value) {
        for (UserStatus status : UserStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}