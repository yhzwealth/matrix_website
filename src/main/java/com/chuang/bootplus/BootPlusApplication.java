package com.chuang.bootplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.chuang.bootplus.mapper")
@EnableScheduling
public class BootPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootPlusApplication.class, args);
    }

}
