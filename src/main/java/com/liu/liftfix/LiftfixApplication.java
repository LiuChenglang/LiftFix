package com.liu.liftfix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liu.liftfix.mapper")
public class LiftfixApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiftfixApplication.class, args);
    }

}
