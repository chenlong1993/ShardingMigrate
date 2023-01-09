package com.sharding.migrate.core.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName DynamicRoutingDataSource
 * @Author ：daiyu
 * @Date 2023-01-09 17:37
 * @Description：动态数据源
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }
}
