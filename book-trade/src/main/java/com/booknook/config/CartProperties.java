package com.booknook.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "book-trade.cart")
public class CartProperties {
    private Integer maxItems = 10;
}