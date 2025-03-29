package com.booknook.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单详情id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * sku商品id
     */
    private Long itemId;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 商品标题
     */
    private String name;

    /**
     * 商品动态属性键值集
     */
    //private String spec;

    /**
     * 价格,单位：分
     */
    private Integer price;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
