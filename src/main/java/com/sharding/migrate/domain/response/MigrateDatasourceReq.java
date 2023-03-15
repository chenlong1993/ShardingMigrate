package com.sharding.migrate.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 迁移数据源表
 */
@Getter
@Setter
public class MigrateDatasourceReq {
    /**
     * 源数据库
     */
    private String originDatabase;

    /**
     * 源数据库url
     */
    private String originUrl;

    /**
     * 源数据库用户名
     */
    private String originUsername;

    /**
     * 源数据库密码
     */
    private String originPassword;

    /**
     * 数据库驱动
     */
    private String originDriverClass;

    /**
     * 目标数据库
     */
    private String targetDatabase;

    /**
     * 源数据库url
     */
    private String targetUrl;

    /**
     * 源数据库用户名
     */
    private String targetUsername;

    /**
     * 源数据库密码
     */
    private String targetPassword;
    /**
     * 数据库驱动
     */
    private String targetDriverClass;

    /**
     * 数据源数据库真实表
     */
    private String originRealTable;

    /**
     * 目标数据库真实表
     */
    private String targetRealTable;

    /**
     * 迁移规则ID
     */
    private Integer migrateRuleId;

}