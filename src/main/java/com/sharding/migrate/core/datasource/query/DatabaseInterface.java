package com.sharding.migrate.core.datasource.query;

public interface DatabaseInterface {

    /**
     * 获得表的字段
     * @param tableName 表名称
     * @return 表字段
     */
    String getTableFields(String tableName);
    /**
     * 获取主键字段
     *
     * @return
     */
    String getSQLQueryPrimaryKey();


    /**
     * 获取主键字段
     *
     * @return
     */
    String getTablePrimaryKey();

    String getSQLQueryTableNameComment();

    String getSQLQueryTablesNameComments();

    /**
     * 获取所有表名的sql
     *
     * @return
     */
    String getSQLQueryTables(String... tableSchema);
    /**
     * 获取 Table schema
     *
     * @return
     */
    String getSQLQueryTableSchema(String... args);

    /**
     * 获取所有的字段的sql
     *
     * @return
     */
    String getSQLQueryColumns(String... args);

    /**
     * 获取所有表名的sql
     *
     * @return
     */
    String getSQLQueryTables();


    /**
     * 获取当前表maxId
     * @param tableName
     * @param primaryKey
     * @return
     */
    String getMaxId(String tableName,String primaryKey);

    /**
     * 获取表和字段注释的sql语句
     *
     * @return The SQL to launch.
     */
    String getSQLQueryComment(String schemaName, String tableName, String columnName);

}
