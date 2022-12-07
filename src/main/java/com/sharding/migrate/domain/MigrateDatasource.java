package com.sharding.migrate.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 迁移数据源表
    */
@Getter
@Setter
@ToString
public class MigrateDatasource {
    /**
    * 主键
    */
    private Long id;

    /**
    * 源数据库
    */
    private String sourceDatabase;

    /**
    * 源数据库url
    */
    private String sourceUrl;

    /**
    * 源数据库用户名
    */
    private String sourceUsername;

    /**
    * 源数据库密码
    */
    private String sourcePassword;

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
    * 数据源数据库真实表
    */
    private String sourceRealTable;

    /**
    * 目标数据库真实表
    */
    private String targetRealTable;

    /**
    * 迁移规则ID
    */
    private Integer migrateRuleId;

    /**
    * 记录创建时间
    */
    private Date createTime;

    /**
    * 记录更新时间
    */
    private Date updateTime;
}