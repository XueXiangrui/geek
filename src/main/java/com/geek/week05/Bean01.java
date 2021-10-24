package com.geek.week05;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xiangrui.xue on 2021/10/25.
 */
public class Bean01 {

//    XML https://blog.csdn.net/echizao1839/article/details/88063013
@Autowired
HikariConfig hikariConfig;
String url = hikariConfig.url;
String name = hikariConfig.username;
String pwd = hikariConfig.password;


}
