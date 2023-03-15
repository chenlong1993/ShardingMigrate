package com.sharding.migrate.core.datasource.mate;

import com.sharding.migrate.core.datasource.query.BaseDatabaseMeta;
import com.sharding.migrate.core.datasource.query.DatabaseInterface;

/**
 * MySQL数据库 meta信息查询
 */
public class MySQLDatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface {

    private volatile static MySQLDatabaseMeta single;

    public static MySQLDatabaseMeta getInstance() {
        if (single == null) {
            synchronized (MySQLDatabaseMeta.class) {
                if (single == null) {
                    single = new MySQLDatabaseMeta();
                }
            }
        }
        return single;
    }

    @Override
    public String getSQLQueryComment(String schemaName, String tableName, String columnName) {
        return String.format("SELECT COLUMN_COMMENT FROM information_schema.COLUMNS where TABLE_SCHEMA = '%s' and TABLE_NAME = '%s' and COLUMN_NAME = '%s'", schemaName, tableName, columnName);
    }

    @Override
    public String getSQLQueryPrimaryKey() {
        return "select column_name from information_schema.columns where table_schema=? and table_name=? and column_key = 'PRI'";
    }

    @Override
    public String getSQLQueryTables(String... tableSchema) {
        return null;
    }

    @Override
    public String getSQLQueryTableSchema(String... args) {
        return null;
    }

    @Override
    public String getSQLQueryTables() {
        return "show tables";
    }

    @Override
    public String getMaxId(String tableName, String primaryKey) {
        return null;
    }

    @Override
    public String getSQLQueryColumns(String... args) {
        return "select column_name from information_schema.columns where table_schema=? and table_name=?";
    }
}
