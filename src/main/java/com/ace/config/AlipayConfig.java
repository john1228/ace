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
@ConfigurationProperties(prefix = "alipay")
@Setter
@Getter
public class AlipayConfig {
    private String seller;
    private String privateKey;
    private String publicKey;
}
