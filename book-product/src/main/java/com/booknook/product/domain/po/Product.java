package com.booknook.product.domain.po;

import com.booknook.common.enums.ProductCondition;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.Year;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private Long uid;
    private String name;
    private String isbn;
    private String publisher;
    private Year publishTime;
    private String author;
    private String category;
    private String description;
    private ProductCondition condition;
    private Integer status;
    private Integer price;
    private Integer stock;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
