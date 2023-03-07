package com.hong.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: EPMS
 * @author: 黄海宏
 * @create: 2023-01-09 16:53
 **/
@SpringBootApplication
@MapperScan("com.hong.server.mapper")
@EnableScheduling
public class EPMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(EPMSApplication.class, args);
    }
}
