package com.booknook.product.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cover {
    private Long id;
    private Long pid;
    private String cover;
    private Integer number;
}
