package config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;


/**
 * Created by xiangrui.xue on 2021/11/9.
 * 对datasource进行设置、获取、清除
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    private static final ThreadLocal<String> contentHolder = new ThreadLocal<String>();

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object,Object> targetDataSource){
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSource);
        super.afterPropertiesSet();
    }

    public static void setDataSource(String dataSource){
        contentHolder.set(dataSource);
    }

    public static String getDataSource(){return contentHolder.get();}

    public static void clearDataSource(){contentHolder.remove();}
}
