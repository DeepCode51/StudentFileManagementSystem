package com.example.sfm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.sfm.mapper.**")
public class SfmSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SfmSystemApplication.class, args);
    }

}
