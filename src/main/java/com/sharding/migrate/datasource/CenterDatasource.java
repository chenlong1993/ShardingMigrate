package com.sharding.migrate.datasource;

import com.huice.middleware.distributor.config.yml.CustomerYamlFactory;
import com.huice.middleware.distributor.exception.MdAssert;
import com.sharding.migrate.config.CustomerYamlFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import static com.huice.middleware.distributor.config.yml.CenterConfig.*;
import static com.huice.middleware.distributor.config.yml.CenterConfig.DATASOURCE_DB_URL;
import static com.huice.middleware.distributor.config.yml.CenterConfig.DATASOURCE_PASSWORD;
import static com.huice.middleware.distributor.config.yml.CenterConfig.DATASOURCE_USERNAME;

public class CenterDatasource {

    private static String JDBC_DRIVER;
    private static String DB_URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static DataSource dataSource;
    private static CenterJdbcTemplate centerJdbcTemplate;
    private static TransactionTemplate transactionTemplate;

    static {
        DB_URL = CustomerYamlFactory.getProperties(DATASOURCE_DB_URL).toString();
//        MdAssert.checkTrue(StringUtils.isBlank(DB_URL), new RuntimeException("Center Config Datasource Properties db_url Can Not Be Null !"));
        USERNAME = CustomerYamlFactory.getProperties(DATASOURCE_USERNAME).toString();
//        MdAssert.checkTrue(StringUtils.isBlank(USERNAME), new RuntimeException("Center Config Datasource Properties username Can Not Be Null !"));
        PASSWORD = CustomerYamlFactory.getProperties(DATASOURCE_PASSWORD).toString();
//        MdAssert.checkTrue(StringUtils.isBlank(PASSWORD), new RuntimeException("Center Config Datasource Properties password Can Not Be Null !"));
        try {
            dataSource = new SimpleDriverDataSource(DriverManager.getDriver(DB_URL), DB_URL, USERNAME, PASSWORD);
            centerJdbcTemplate = new CenterJdbcTemplate(getDatasource());
            DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
            transactionTemplate = new TransactionTemplate(dataSourceTransactionManager);
        } catch (SQLException e) {
            throw new RuntimeException("driver load failed", e);
        }
    }

    public static Connection getConn() {
        try {
            return getDatasource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("datasource connecting failed", e);
        }
    }

    public static DataSource getDatasource() {
        return Optional.ofNullable(dataSource).orElse(buildDatasource());

    }

    public static DataSource buildDatasource() {
        try {
            return new SimpleDriverDataSource(DriverManager.getDriver(DB_URL), DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("driver load failed", e);
        }
    }

    public static CenterJdbcTemplate buildJdbcTemplate() {
        return Optional.ofNullable(centerJdbcTemplate).orElse(new CenterJdbcTemplate(getDatasource()));
    }

    public static TransactionTemplate buildTransactionTemplate() {
        return Optional.ofNullable(transactionTemplate).orElse(new TransactionTemplate(new DataSourceTransactionManager(getDatasource())));
    }
}
