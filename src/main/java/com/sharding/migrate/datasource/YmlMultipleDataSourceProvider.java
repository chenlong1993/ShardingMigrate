package com.sharding.migrate.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.sharding.migrate.config.MultipleDSConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName YmlMultipleDataSourceProvider
 * @Author ：daiyu
 * @Date 2022-12-10 17:03
 * @Description：多数据源提供者
 */
@Component
@Configuration
public class YmlMultipleDataSourceProvider implements MultipleDataSourceProvider {
    @Autowired
    private MultipleDSConfiguration multipleDSConfiguration;

    @Override
    public Map<String, DataSource> loadDataSource() {
        Map<String, Map<String, String>> myDS = multipleDSConfiguration.getDs();
        Map<String, DataSource> map = new HashMap<>(myDS.size());
        try {
            for (String key : myDS.keySet()) {
                DruidDataSource druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(myDS.get(key));
                map.put(key, multipleDSConfiguration.dataSource(druidDataSource));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
