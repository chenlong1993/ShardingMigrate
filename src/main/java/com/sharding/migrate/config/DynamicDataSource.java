package com.sharding.migrate.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @ClassName DynamicDataSource
 * @Author ：daiyu
 * @Date 2022-12-25 22:30
 * @Description：
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    private boolean debug = true;
    private Map<Object, Object> dynamicTargetDataSources;
    private Object dynamicDefaultTargetDataSource;
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
