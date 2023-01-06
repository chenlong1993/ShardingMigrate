package com.sharding.migrate.core.model;

import com.sharding.migrate.domain.Databasesource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MigrateMongoDBModel
 * @Author ：daiyu
 * @Date 2023-01-06 22:10
 * @Description：用于传参，构建json
 */
@Getter
@Setter
public class MigrateMongoDBModel {

    /**
     * mongo列名
     */
    private List<Map<String, Object>> columns;

    /**
     * 数据源信息
     */
    private Databasesource databasesource;

    private String address;

    private String dbName;

    private String readerTable;

    private String writerTable;
}