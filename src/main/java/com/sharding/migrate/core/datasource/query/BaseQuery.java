package com.sharding.migrate.core.datasource.query;

import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.util.JdbcUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.sharding.migrate.common.constants.Constants;
import com.sharding.migrate.common.util.StrUtil;
import com.sharding.migrate.domain.MigrateDatasource;
import com.sharding.migrate.domain.database.ColumnInfo;
import com.sharding.migrate.domain.database.DasColumn;
import com.sharding.migrate.domain.database.TableInfo;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 抽象查询工具
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName BaseQueryTool
 * @Version 1.0
 * @since 2019/7/18 9:22
 */
@Slf4j
public abstract class BaseQuery implements QueryToolInterface {

    /**
     * 用于获取查询语句
     */
    private DatabaseInterface sqlBuilder;

    private DataSource datasource;

    private Connection connection;
    /**
     * 当前数据库名
     */
    private String currentSchema;
    private String currentDatabase;

    private String originSchema;
    private String originDatabase;

    private String targetSchema;
    private String targetDatabase;

    /**
     * 构造方法,预先加载各种数据库信息
     *
     * @param migrateDatasource
     */
    BaseQuery(MigrateDatasource migrateDatasource) throws SQLException {
        //todo 优先从本地缓存获得
        getOriginDataSource(migrateDatasource);

        sqlBuilder = DatabaseMetaFactory.getByDbType(migrateDatasource.getOriginDatabase());
        currentSchema = getSchema(migrateDatasource.getOriginUsername());
        currentDatabase = migrateDatasource.getOriginDatabase();

    }

    public String getCurrentSchema() {
        return currentSchema;
    }


    /**
     * 获得原始数据
     *
     * @param migrateDatasource
     * @throws SQLException
     */
    private void getOriginDataSource(MigrateDatasource migrateDatasource) throws SQLException {
        //todo 加入密码加解密

        //这里默认使用 hikari 数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(migrateDatasource.getOriginUsername());
        dataSource.setPassword(migrateDatasource.getOriginPassword());
        dataSource.setJdbcUrl(migrateDatasource.getOriginUrl());
        dataSource.setDriverClassName(migrateDatasource.getOriginDriverClass());
        dataSource.setMaximumPoolSize(1);
        dataSource.setMinimumIdle(0);
        dataSource.setConnectionTimeout(30000);
        this.datasource = dataSource;
        this.connection = this.datasource.getConnection();
    }

    /**
     * 获得目标数据
     *
     * @param migrateDatasource
     * @throws SQLException
     */
    private void getTargetDataSource(MigrateDatasource migrateDatasource) throws SQLException {
        //todo 加入密码加解密

        //这里默认使用 hikari 数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(migrateDatasource.getOriginUsername());
        dataSource.setPassword(migrateDatasource.getOriginPassword());
        dataSource.setJdbcUrl(migrateDatasource.getOriginUrl());
        dataSource.setDriverClassName(migrateDatasource.getOriginDriverClass());
        dataSource.setMaximumPoolSize(1);
        dataSource.setMinimumIdle(0);
        dataSource.setConnectionTimeout(30000);
        this.datasource = dataSource;
        this.connection = this.datasource.getConnection();
    }

    //根据connection获取schema
    private String getSchema(String jdbcUsername) {
        String res = null;
        try {
            res = connection.getCatalog();
        } catch (SQLException e) {
            try {
                res = connection.getSchema();
            } catch (SQLException e1) {
                log.error("[SQLException getSchema Exception] --> "
                        + "the exception message is:" + e1.getMessage());
            }
            log.error("[getSchema Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        // 如果res是null，则将用户名当作 schema
//        if (StrUtil.isBlank(res) && StringUtils.isNotBlank(jdbcUsername)) {
//            res = jdbcUsername.toUpperCase();
//        }
        return res;
    }

    @Override
    public TableInfo buildTableInfo(String tableName) {
        //获取表信息
        List<Map<String, Object>> tableInfos = this.getTableInfo(tableName);
        if (tableInfos.isEmpty()) {
            throw new NullPointerException("查询出错! ");
        }

        TableInfo tableInfo = new TableInfo();
        //表名，注释
        List tValues = new ArrayList(tableInfos.get(0).values());

        tableInfo.setName(StrUtil.toString(tValues.get(0)));
        tableInfo.setComment(StrUtil.toString(tValues.get(1)));


        //获取所有字段
        List<ColumnInfo> fullColumn = getColumns(tableName);
        tableInfo.setColumns(fullColumn);

        //获取主键列
        List<String> primaryKeys = getPrimaryKeys(tableName);
        log.info("主键列为：{}", primaryKeys);

        //设置ifPrimaryKey标志
        fullColumn.forEach(e -> {
            if (primaryKeys.contains(e.getName())) {
                e.setIfPrimaryKey(true);
            } else {
                e.setIfPrimaryKey(false);
            }
        });
        return tableInfo;
    }

    //无论怎么查，返回结果都应该只有表名和表注释，遍历map拿value值即可
    @Override
    public List<Map<String, Object>> getTableInfo(String tableName) {
        String sqlQueryTableNameComment = sqlBuilder.getSQLQueryTableNameComment();
        log.info("查询数据库信息" + sqlQueryTableNameComment);
        List<Map<String, Object>> res = null;
        try {
            res = JdbcUtils.executeQuery(connection, sqlQueryTableNameComment, ImmutableList.of(currentSchema, tableName));
        } catch (SQLException e) {
            log.error("[getTableInfo Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> getTables() {
        String sqlQueryTables = sqlBuilder.getSQLQueryTables();
        log.info(sqlQueryTables);
        List<Map<String, Object>> res = null;
        try {
            res = JdbcUtils.executeQuery(connection, sqlQueryTables, ImmutableList.of(currentSchema));
        } catch (SQLException e) {
            log.error("[getTables Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    @Override
    public List<ColumnInfo> getColumns(String tableName) {

        List<ColumnInfo> fullColumn = Lists.newArrayList();
        //获取指定表的所有字段
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getTableFields(tableName);
            log.info("querySql: {}", querySql);

            //获取所有字段
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            List<DasColumn> dasColumns = buildDasColumn(tableName, metaData);
            statement.close();

            //构建 fullColumn
            fullColumn = buildFullColumn(dasColumns);

        } catch (SQLException e) {
            log.error("[getColumns Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return fullColumn;
    }

    private List<ColumnInfo> buildFullColumn(List<DasColumn> dasColumns) {
        List<ColumnInfo> res = Lists.newArrayList();
        dasColumns.forEach(e -> {
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setName(e.getColumnName());
            columnInfo.setComment(e.getColumnComment());
            columnInfo.setType(e.getColumnTypeName());
            columnInfo.setIfPrimaryKey(e.isIsprimaryKey());
            columnInfo.setIsnull(e.getIsNull());
            res.add(columnInfo);
        });
        return res;
    }

    //构建DasColumn对象
    private List<DasColumn> buildDasColumn(String tableName, ResultSetMetaData metaData) {
        List<DasColumn> res = Lists.newArrayList();
        try {
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                DasColumn dasColumn = new DasColumn();
                dasColumn.setColumnClassName(metaData.getColumnClassName(i));
                dasColumn.setColumnTypeName(metaData.getColumnTypeName(i));
                dasColumn.setColumnName(metaData.getColumnName(i));
                dasColumn.setIsNull(metaData.isNullable(i));

                res.add(dasColumn);
            }

            Statement statement = connection.createStatement();

            if (currentDatabase.equals(JdbcConstants.MYSQL) || currentDatabase.equals(JdbcConstants.ORACLE)) {
                DatabaseMetaData databaseMetaData = connection.getMetaData();

                ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);

                while (resultSet.next()) {
                    String name = resultSet.getString("COLUMN_NAME");
                    res.forEach(e -> {
                        if (e.getColumnName().equals(name)) {
                            e.setIsprimaryKey(true);

                        } else {
                            e.setIsprimaryKey(false);
                        }
                    });
                }

                res.forEach(e -> {
                    String sqlQueryComment = sqlBuilder.getSQLQueryComment(currentSchema, tableName, e.getColumnName());
                    //查询字段注释
                    try {
                        ResultSet resultSetComment = statement.executeQuery(sqlQueryComment);
                        while (resultSetComment.next()) {
                            e.setColumnComment(resultSetComment.getString(1));
                        }
                        JdbcUtils.close(resultSetComment);
                    } catch (SQLException e1) {
                        log.error("[buildDasColumn executeQuery Exception] --> "
                                + "the exception message is:" + e1.getMessage());
                    }
                });
            }

            JdbcUtils.close(statement);
        } catch (SQLException e) {
            log.error("[buildDasColumn Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    //获取指定表的主键，可能是多个，所以用list
    private List<String> getPrimaryKeys(String tableName) {
        List<String> res = Lists.newArrayList();
        String sqlQueryPrimaryKey = sqlBuilder.getSQLQueryPrimaryKey();
        try {
            List<Map<String, Object>> pkColumns = JdbcUtils.executeQuery(connection, sqlQueryPrimaryKey, ImmutableList.of(currentSchema, tableName));
            //返回主键名称即可
            pkColumns.forEach(e -> res.add((String) new ArrayList<>(e.values()).get(0)));
        } catch (SQLException e) {
            log.error("[getPrimaryKeys Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    @Override
    public List<String> getColumnNames(String tableName, String datasource) {

        List<String> res = Lists.newArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getTableFields(tableName);
            log.info("querySql: {}", querySql);

            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                if (JdbcConstants.HIVE.equals(datasource)) {
                    if (columnName.contains(Constants.SPLIT_POINT)) {
                        res.add(i - 1 + Constants.SPLIT_SCOLON + columnName.substring(columnName.indexOf(Constants.SPLIT_POINT) + 1) + Constants.SPLIT_SCOLON + metaData.getColumnTypeName(i));
                    } else {
                        res.add(i - 1 + Constants.SPLIT_SCOLON + columnName + Constants.SPLIT_SCOLON + metaData.getColumnTypeName(i));
                    }
                } else {
                    res.add(columnName);
                }

            }
        } catch (SQLException e) {
            log.error("[getColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return res;
    }

    @Override
    public List<String> getTableNames(String tableSchema) {
        List<String> tables = new ArrayList<String>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLQueryTables(tableSchema);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tableName = rs.getString(1);
                tables.add(tableName);
            }
            tables.sort(Comparator.naturalOrder());
        } catch (SQLException e) {
            log.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return tables;
    }

    @Override
    public List<String> getTableNames() {
        List<String> tables = new ArrayList<String>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLQueryTables();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tableName = rs.getString(1);
                tables.add(tableName);
            }
        } catch (SQLException e) {
            log.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return tables;
    }

    public Boolean dataSourceTest() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            if (metaData.getDatabaseProductName().length() > 0) {
                return true;
            }
        } catch (SQLException e) {
            log.error("[dataSourceTest Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return false;
    }


    protected String getSQLQueryTables(String tableSchema) {
        return sqlBuilder.getSQLQueryTables(tableSchema);
    }

    /**
     * 不需要其他参数的可不重写
     *
     * @return
     */
    protected String getSQLQueryTables() {
        return sqlBuilder.getSQLQueryTables();
    }

    @Override
    public List<String> getColumnsByQuerySql(String querySql) throws SQLException {

        List<String> res = Lists.newArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            querySql = querySql.replace(";", "");
            //拼装sql语句，在后面加上 where 1=0 即可
            String sql = querySql.concat(" where 1=0");
            //判断是否已有where，如果是，则加 and 1=0
            //从最后一个 ) 开始找 where，或者整个语句找
            if (querySql.contains(")")) {
                if (querySql.substring(querySql.indexOf(")")).contains("where")) {
                    sql = querySql.concat(" and 1=0");
                }
            } else {
                if (querySql.contains("where")) {
                    sql = querySql.concat(" and 1=0");
                }
            }
            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                res.add(metaData.getColumnName(i));
            }
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return res;
    }

    @Override
    public long getMaxIdVal(String tableName, String primaryKey) {
        Statement stmt = null;
        ResultSet rs = null;
        long maxVal = 0;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLMaxID(tableName, primaryKey);
            rs = stmt.executeQuery(sql);
            rs.next();
            maxVal = rs.getLong(1);
        } catch (SQLException e) {
            log.error("[getMaxIdVal Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }


        return maxVal;
    }

    @Override
    public List<String> getPrimaryKey(String schema, String tableName) throws SQLException {
        String primaryKeyByTableSql = sqlBuilder.getPrimaryKeyByTable(schema,tableName);
        return getColumnsByQuerySql(primaryKeyByTableSql);
    }

    private String getSQLMaxID(String tableName, String primaryKey) {
        return sqlBuilder.getMaxId(tableName, primaryKey);
    }

    public void executeCreateTableSql(String querySql) {
        if (StringUtils.isBlank(querySql)) {
            return;
        }
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(querySql);
        } catch (SQLException e) {
            log.error("[executeCreateTableSql Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(stmt);
        }
    }


    public List<String> getTableSchema() {
        List<String> schemas = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLQueryTableSchema();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tableName = rs.getString(1);
                schemas.add(tableName);
            }
        } catch (SQLException e) {
            log.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return schemas;
    }

    protected String getSQLQueryTableSchema() {

        return sqlBuilder.getSQLQueryTableSchema();
    }
}
