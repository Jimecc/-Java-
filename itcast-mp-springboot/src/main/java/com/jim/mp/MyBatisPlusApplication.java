package com.jim.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月05日
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
@MapperScan("com.jim.mp.mapper")
public class MyBatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class,args);
    }
}
