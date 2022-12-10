package com.sharding.migrate.datasource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @ClassName MultipleDataSourceProvider
 * @Author ：daiyu
 * @Date 2022-12-10 17:02
 * @Description：多数据源提供者
 */
public interface MultipleDataSourceProvider {
    String DEFAULT_DATASOURCE = "master";

    Map<String, DataSource> loadDataSource();
}
