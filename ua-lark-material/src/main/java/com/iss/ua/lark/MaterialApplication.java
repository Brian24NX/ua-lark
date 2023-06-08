package com.iss.ua.lark;

import com.hanson.mybatis.config.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Hanson
 * @date 2021/11/18  17:49
 */
@SpringBootApplication(exclude = {MybatisPlusConfig.class})
@MapperScan(basePackages = "com.iss.ua.lark.dao.mappers.")
public class  MaterialApplication {
    public static void main(String[] args) {
        SpringApplication.run(MaterialApplication.class, args);
    }
}
