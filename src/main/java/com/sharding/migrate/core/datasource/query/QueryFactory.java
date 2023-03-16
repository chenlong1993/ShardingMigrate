package com.sharding.migrate.core.datasource.query;

import com.sharding.migrate.common.util.JdbcConstants;
import com.sharding.migrate.common.util.RdbmsException;
import com.sharding.migrate.domain.MigrateDatasource;

/**
 * @ClassName QueryFactory
 * @Author ：daiyu
 * @Date 2023-03-15 16:49
 * @Description：数据源查询工厂，创建各种数据库查询对象，查询各种数据源的元信息
 */
public class QueryFactory {
    /**
     * 数据源
     *
     * @param migrateDatasource 数据源配置信息
     * @return
     */
    public static BaseQuery getByDbType(MigrateDatasource migrateDatasource) {
        //获取dbType
        String datasource = migrateDatasource.getOriginDatabase();
        if (JdbcConstants.MYSQL.equals(datasource)) {
            return getMySQLQueryToolInstance(migrateDatasource);
        } else if (JdbcConstants.MYSQL.equals(datasource)) {
            return getPgQueryToolInstance(migrateDatasource);
        }
        throw new UnsupportedOperationException("找不到该类型: ".concat(datasource));
    }

    public static BaseQuery getOriginByDbType(MigrateDatasource migrateDatasource) {
        //获取dbType
        String datasource = migrateDatasource.getOriginDatabase();
        if (JdbcConstants.MYSQL.equals(datasource)) {
            return getMySQLQueryToolInstance(migrateDatasource);
        } else if (JdbcConstants.MYSQL.equals(datasource)) {
            return getPgQueryToolInstance(migrateDatasource);
        }
        throw new UnsupportedOperationException("找不到该类型: ".concat(datasource));
    }

    public static BaseQuery getTargetByDbType(MigrateDatasource migrateDatasource) {
        //获取dbType
        String datasource = migrateDatasource.getTargetDatabase();
        if (JdbcConstants.MYSQL.equals(datasource)) {
            return getMySQLQueryToolInstance(migrateDatasource);
        } else if (JdbcConstants.MYSQL.equals(datasource)) {
            return getPgQueryToolInstance(migrateDatasource);
        }
        throw new UnsupportedOperationException("找不到该类型: ".concat(datasource));
    }
    private static BaseQuery getMySQLQueryToolInstance(MigrateDatasource migrateDatasource) {
        try {
            return new MySQLQueryTool(migrateDatasource);
        } catch (Exception e) {
            throw RdbmsException.asConnException(JdbcConstants.MYSQL,
                    e, migrateDatasource.getOriginUsername(), migrateDatasource.getOriginDatabase());
        }
    }

    private static BaseQuery getPgQueryToolInstance(MigrateDatasource migrateDatasource) {
        try {
            return new PostgresqlQueryTool(migrateDatasource);
        } catch (Exception e) {
            throw RdbmsException.asConnException(JdbcConstants.MYSQL,
                    e, migrateDatasource.getOriginUsername(), migrateDatasource.getOriginDatabase());
        }
    }

}
