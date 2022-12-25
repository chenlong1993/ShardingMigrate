package com.sharding.migrate.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Author ：daiyu
 * @Date 2022-12-25 22:23
 * @Description：数据源配置
 */
@Component
public class DataSourceConfig {
    @Bean
    public DataSource sourceDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource targetDataSources() {
        return DataSourceBuilder.create().build();
    }


}
