package config;

import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by xiangrui.xue on 2021/11/10.
 */
@Configuration  //todo 跟DatasourceConfig是相通的吗？可以直接调用DatasourceConfig的内容
public class ShardingJdbcConfig {

    @Autowired
    private DataSource masterDataSource;

    @Autowired
    private DataSource slaveDataSource;

    @Bean
    @Primary
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {
        Map<String,DataSource> dataSourceMap = new HashMap<String, DataSource>();
        dataSourceMap.put("write",masterDataSource);
        dataSourceMap.put("slave",slaveDataSource);

        //跟1.0比香到不行
        MasterSlaveRuleConfiguration masterSlaveRuleConfiguration = new MasterSlaveRuleConfiguration
                ("ds_write_read", "write", Collections.singletonList("read"));
        Properties properties = new Properties();
        properties.setProperty("sql.show",String.valueOf(true));
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap,
                masterSlaveRuleConfiguration,properties);
        return dataSource;
    }
}
