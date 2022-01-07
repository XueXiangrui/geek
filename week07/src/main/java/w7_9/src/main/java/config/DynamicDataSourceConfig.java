package config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.management.MXBean;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiangrui.xue on 2021/11/9.
 * todo 不了解dataSource相关的类。。。DynamicDataSource是干什么的
 */
@Configuration
@Component
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "datasource.write")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.read")
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource,DataSource slaveDataSource){
        Map<Object,Object> targetDataSources = new HashMap();
        targetDataSources.put("write",masterDataSource);
        targetDataSources.put("read",slaveDataSource);
        return new DynamicDataSource(masterDataSource,targetDataSources);
    }
}
