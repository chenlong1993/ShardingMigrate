package com.sharding.migrate.core.datasource.query;


import com.alibaba.druid.util.JdbcConstants;
import com.sharding.migrate.core.datasource.mate.MySQLDatabaseMeta;
import com.sharding.migrate.core.datasource.mate.PostgresqlDatabaseMeta;

/**
 * meta信息工厂
 */
public class DatabaseMetaFactory {

    //根据数据库类型返回对应的接口
    public static DatabaseInterface getByDbType(String dbType) {
        if (JdbcConstants.MYSQL.equals(dbType)) {
            return MySQLDatabaseMeta.getInstance();
        } else if (JdbcConstants.POSTGRESQL.equals(dbType)) {
            return PostgresqlDatabaseMeta.getInstance();
        }
        throw new UnsupportedOperationException("暂不支持的类型：".concat(dbType));
    }
}

