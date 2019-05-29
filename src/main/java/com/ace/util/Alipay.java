package com.ace.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import static com.alipay.api.AlipayConstants.CHARSET;

/**
 * @author john
 * @date 19-5-18 下午7:39
 */
public enum Alipay {
    Instance;
    private final String getway = "https://openapi.alipay.com/gateway.do";
    private final String appId = "2019030863456715";
    private final String privateKey =
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDhPypby5r8Z3cu\n" +
                    "RZwRhwO+yikCkHl/w/wZMoEMBirLMqgq9JvD4REmJ2bH8lu+0iB6Ejtoz+DXPEc5\n" +
                    "ZUn98kvwVbICwEqjsZX5h3LXQHAGXBZGyp3w287+lQgFwzxJwYsNs9+wvgru2z8A\n" +
                    "b80dcbp3L5qSZ9c1JONmXYJ5M9uwnISYXZwCkRPK0IsaqPzZR5EVvHoV3ouag1R5\n" +
                    "gKmMadtRD4I31kDrG2gkiPUpnFhNYlODu0z5Jx5dCCjCoPYQwDd8JcAQ2oxtCYk+\n" +
                    "rTQMsAb2gQKZLkbp7hSDProHPgCwdyUDJcIVO39PjSqZ46CWE4LsDnRG/yy7/T36\n" +
                    "Tv27SyqXAgMBAAECggEBAMjh3JUhcIfKfq/1xMqc7uoowYpX7wPTdfHC6PQgS+oM\n" +
                    "s44buQ9omjK80R1hOBFmtTApMnK3cn6Cc8LsYqDohnFA/BEYDXkP2AFCm1j9Tutd\n" +
                    "FUDtUw6L+n9xykXZaNsekTUXRlgKJRYA0p9BFl08IGBLRD9t/LCYxXV1z/boGPXX\n" +
                    "9NA4pOZq4byDkRKQAZTQ4ZYWYWFMXzLREc3knvTkffVbET2nwubsroPPY5MxMJ9X\n" +
                    "U7npFd7ylapKkEB+Yi0b/2qf5US3hl3HpNUTi95JqCeY/ks8r5SEoypsrc4kEEXL\n" +
                    "RPpy8ooGQZ7b9XZ7unWqiFMfG4I9GSZ5YWpDfkqlFAECgYEA8tZ/2Eg/Jt2QFhW6\n" +
                    "1bi4y5pNonLxwpSGaE46GHTuUTHk/qi92KIb1muisI5elDNPCn4ey+cIU6G8FNJu\n" +
                    "8j2nXU/PIk5ZP9R3LvOvpi/gYeeytOafMgabIhEYCMzqXnI7yJx8/5Yow7Kuen7r\n" +
                    "lQqAY/f9rIVpJDJUhJ5UyAVdm/cCgYEA7XSUcGhT/f5l49umF9QpDuUT6qoVOAhe\n" +
                    "l8xKOYVaGWQVq+Lw4VFo3+2/nzUvUB4+yoXwz4ZGXdeJxYqEIuO2wZ3MxUOs6P1s\n" +
                    "tWkWf47twCxN5GtzSGQciCO7pSB1jyNpNzvgRpv6OWIDh958Ne/oEOCw6u4ebZOZ\n" +
                    "aYXdBPGM/mECgYAJCZCSdQXcpWxCViaw44IFGcX/4LU8n5hs81RYKqnf3bJ/A7ql\n" +
                    "AEt27GSOm43LiClN46mzvvJhYmiEgdxdKYw7/cdgqHfL11VkKu+DiM6Z9iyBbP/J\n" +
                    "tdM6LlatpSqnvyqYabKBNI5p0hGKP4T29AAZOFZ5gDGMhk787IONOh3kuwKBgDYL\n" +
                    "Ro2e8R7p+760i0T5IK8nLDVIqLOsE/twn6Qf2bXVtaj9ad2ebcQplGdj73TIrXni\n" +
                    "qkBRS0qod87iC9j1Au9mPujW0k/k/gz78gbuPOZ0LvZCDmb3ghbqKxcWICuH37aW\n" +
                    "hiEL9xOAX1VZKeQMykCtIxcVQw+lfFEsCnXJlFGBAoGAfVy1jlRp1HMWZWbfnuxm\n" +
                    "yzwWHRR/6FkZPU6y+Ky25f5jkottdDsEKgBvj1Cp/xedpZviFIrw5TryirMg2WYO\n" +
                    "m/aYmPClsAukOzWVZJfGk1Ksr/izLrX0uL4+Oz7curdWPRvIyqDzhj6kHMwpxQ+a\n" +
                    "XIlCtoiwq50gW3Wy5A2M8Y8=";
    private final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkvlK17Q7AMXNHZxh9vOZJWMf4N7/TmGVDSv4k8gx4h3XyuWXkdilMcSGKzw/6nFSLHReAhG/aTXHPLehtPH0SQrcAgM2SH+UrPNfCcg6KgJyBWl4Cxmj/BPL+KEDnixTB1sAQo41/niHBPyN24T/j24xBoMjMwjtok4pzl9IFGUvjg4QbdstUGcW3WndlIubTUOFVG6K/XFDDHuK+w0HGMenl5jCdL8PQEVLGTnszgyKBeuzGYF9/q0lkh4yfoFCCjvS+K7S0XCWgIG3gAjjmh/aZKQCLxuEr4rLiPPFkiTgdEuZEonOVc3kkYWzZ2f/qpTOvBxKe3AXrQV57W7SGwIDAQAB";
    private final String format = "json";
    private final String signType = "RSA2";
    private final AlipayClient client;
    private final String notifyUrl = "";

    Alipay() {
        client = new DefaultAlipayClient(getway, appId, privateKey, format, CHARSET, publicKey, signType);
    }

    public String getPay(AlipayTradeAppPayModel payModel) throws AlipayApiException {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setBizModel(payModel);
        request.setNotifyUrl(notifyUrl);
        AlipayTradeAppPayResponse response = client.sdkExecute(request);
        return response.getBody();
    }
}
