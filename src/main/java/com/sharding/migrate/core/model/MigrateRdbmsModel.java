package com.sharding.migrate.core.model;

import com.sharding.migrate.domain.Databasesource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName MigrateRdbmsModel
 * @Author ：daiyu
 * @Date 2023-01-06 22:10
 * @Description：用于传参，构建json
 */
@Getter
@Setter
public class MigrateRdbmsModel {

    /**
     * 表名
     */
    private String tables;

    /**
     * 列名
     */
    private List<String> rdbmsColumns;

    /**
     * 数据源信息
     */
    private Databasesource databasesource;

    /**
     * querySql 属性，如果指定了，则优先于columns参数
     */
    private String querySql;

    /**
     * preSql 属性
     */
    private String preSql;

    /**
     * postSql 属性
     */
    private String postSql;

    /**
     * 切分主键
     */
    private String splitPk;

    /**
     * where
     */
    private String whereParam;

    private String jdbcUsername;
    private String jdbcPassword;

    private String jdbcUrl;

}
