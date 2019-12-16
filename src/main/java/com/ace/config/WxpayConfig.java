package com.ace.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author john
 * @date 19-11-20 下午2:39
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "wxpay")
@Setter
@Getter
public class WxpayConfig {
    private String seller;
    private String appId;
    private String appKey;
}
