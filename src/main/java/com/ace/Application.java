package com.ace;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class Application {
//    @Bean("objectMapper")
//    public ObjectMapper myMapper() {
//        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
