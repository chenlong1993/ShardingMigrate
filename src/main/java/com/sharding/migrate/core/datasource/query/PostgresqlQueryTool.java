package com.sharding.migrate.core.datasource.query;

import com.sharding.migrate.domain.MigrateDatasource;

import java.sql.SQLException;

/**
 * Postgresql数据库使用的查询工具
 */
public class PostgresqlQueryTool extends BaseQuery implements QueryToolInterface {
    public PostgresqlQueryTool(MigrateDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

}
