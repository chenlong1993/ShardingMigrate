package com.sharding.migrate.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * @ClassName DataLoader
 * @Author ：daiyu
 * @Date 2022-12-13 23:29
 * @Description：数据源加载
 */
public class DataLoader {
    private static final String SQL_SHARDING_DATASOURCE = "select * from db_datasource";

    private List<DbDatasource> dbDataSources;
    private DataLoader() {
        try (
                final Connection connection = CenterDatasource.getConn();
                final ResultSet dbDatasourceResultSet = connection.createStatement().executeQuery(SQL_SHARDING_DATASOURCE)){

        }
    }
}
