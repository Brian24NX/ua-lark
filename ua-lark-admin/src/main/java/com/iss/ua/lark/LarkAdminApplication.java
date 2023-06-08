package com.iss.ua.lark;

import com.hanson.mybatis.config.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class , MybatisPlusConfig.class})
@MapperScan(basePackages = "com.iss.ua.lark.**.mapper.")
public class LarkAdminApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(LarkAdminApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  admin启动成功! ");
    }
}
