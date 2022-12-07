package com.sharding.migrate.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 迁移数据规则表
    */
@Getter
@Setter
@ToString
public class MigrateRule {
    /**
    * 主键
    */
    private Long id;

    /**
    * 逻辑表
    */
    private String logicTable;

    /**
    * 库分区键
    */
    private String databaseShardingKey;

    /**
    * 库分区规则
    */
    private String databaseShardingRule;

    /**
    * 表分区键
    */
    private String tableShardingKey;

    /**
    * 表分区规则
    */
    private String tableShardingRule;

    /**
    * 分片算法
    */
    private String shardingAlgorithm;

    /**
    * 记录创建时间
    */
    private Date createTime;

    /**
    * 记录更新时间
    */
    private Date updateTime;
}