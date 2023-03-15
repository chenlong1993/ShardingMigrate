package com.sharding.migrate.core.datasource.query;

import com.sharding.migrate.domain.MigrateDatasource;

import java.sql.SQLException;

/**
 * mysql数据库使用的查询工具
 */
public class MySQLQueryTool extends BaseQuery implements QueryToolInterface {

    public MySQLQueryTool(MigrateDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

}
