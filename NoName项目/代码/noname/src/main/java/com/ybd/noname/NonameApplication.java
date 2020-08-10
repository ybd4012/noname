package com.ybd.noname;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication()
@EnableCaching
public class NonameApplication {
    public static void main(String[] args) {
        SpringApplication.run(NonameApplication.class, args);
    }
}
