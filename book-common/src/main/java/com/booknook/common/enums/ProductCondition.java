package com.booknook.common.enums;

import lombok.Getter;

@Getter
public enum ProductCondition {

    NEW(1, "全新"),
    LIKE_NEW(2, "九成新"),
    GOOD(3, "七成新"),
    ACCEPTABLE(4, "五成新");

    private final int value;

    private final String desc;

    ProductCondition(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public boolean equalsValue(Integer value) {
        if (value == null) {
            return false;
        }
        return getValue() == value;
    }

    public static ProductCondition fromValue(int value) {
        for (ProductCondition condition : ProductCondition.values()) {
            if (condition.getValue() == value) {
                return condition;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}
