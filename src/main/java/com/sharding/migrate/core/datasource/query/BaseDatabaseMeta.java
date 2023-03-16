package com.sharding.migrate.core.datasource.query;

/**
 * meta信息interface
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName BaseDatabaseMeta
 * @Version 1.0
 * @since 2019/7/17 15:45
 */
public abstract class BaseDatabaseMeta implements DatabaseInterface {

    @Override
    public String getTableFields(String tableName) {
        return "SELECT * FROM " + tableName + " where 1=0";
    }

    @Override
    public String getSQLQueryTablesNameComments() {
        return "select table_name,table_comment from information_schema.tables where table_schema=?";
    }

    @Override
    public String getSQLQueryTableNameComment() {
        return "select table_name,table_comment from information_schema.tables where table_schema=? and table_name = ?";
    }

    @Override
    public String getTablePrimaryKey() {
        return "";
    }

    @Override
    public String getSQLQueryColumns(String... args) {
        return null;
    }

    @Override
    public String getPrimaryKeyByTable(String schema, String tableName) {
        return "SELECT column_name FROM INFORMATION_SCHEMA.`KEY_COLUMN_USAGE` WHERE  CONSTRAINT_SCHEMA = ? AND table_name = ? AND constraint_name = 'PRIMARY'\n";
    }

}
