package com.booknook.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "book-gateway.auth")
public class AuthProperties {
    private List<String> includePaths;
    private List<String> excludePaths;
}
