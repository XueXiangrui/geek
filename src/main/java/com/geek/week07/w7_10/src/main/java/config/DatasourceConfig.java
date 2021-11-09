package config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by xiangrui.xue on 2021/11/10.
 */
@Configuration
public class DatasourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "datasource.write")
    public DataSource masterDataSource(){return DataSourceBuilder.create().build();}

    @Bean
    @ConfigurationProperties(prefix = "datasource.read")
    public DataSource slaveDataSource(){return DataSourceBuilder.create().build();}
}
