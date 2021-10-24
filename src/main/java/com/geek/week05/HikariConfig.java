package com.geek.week05;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by xiangrui.xue on 2021/10/25.
 */
@Configuration
@PropertySource("classpath:hikari.xml")
public class HikariConfig {

        @Value("${jdbcUrl}")
        String url ;

        @Value("${username}")
        String username;

        @Value("${password}")
        String password;
}
