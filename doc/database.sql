create table migrate_datasource
(
    `id`                bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `source_database`   varchar(50)  not null DEFAULT '' COMMENT '源数据库',
    `source_url`        varchar(50)  not null DEFAULT '' COMMENT '源数据库url',
    `source_username`   varchar(50)  not null DEFAULT '' COMMENT '源数据库用户名',
    `source_password`   varchar(50)  not null DEFAULT '' COMMENT '源数据库密码',
    `target_database`   varchar(50)  not null DEFAULT '' COMMENT '目标数据库',
    `target_url`        varchar(50)  not null DEFAULT '' COMMENT '源数据库url',
    `target_username`   varchar(50)  not null DEFAULT '' COMMENT '源数据库用户名',
    `target_password`   varchar(50)  not null DEFAULT '' COMMENT '源数据库密码',
    `source_real_table` varchar(100) not null COMMENT '数据源数据库真实表',
    `target_real_table` varchar(100) not null COMMENT '目标数据库真实表',
    `migrate_rule_id`   int(8)                DEFAULT NULL COMMENT '迁移规则ID',
    `create_time`            datetime              default CURRENT_TIMESTAMP not null COMMENT '记录创建时间',
    `update_time`            datetime              default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP COMMENT '记录更新时间',
    PRIMARY KEY (`id`)
) comment '迁移数据源表';
create table migrate_rule
(
    `id`                     bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `logic_table`            varchar(100) not null COMMENT '逻辑表',
    `database_sharding_key`  varchar(50)  not null DEFAULT '' COMMENT '库分区键',
    `database_sharding_rule` varchar(50)  not null DEFAULT '' COMMENT '库分区规则',
    `table_sharding_key`     varchar(50)  not null DEFAULT '' COMMENT '表分区键',
    `table_sharding_rule`    varchar(50)  not null DEFAULT '' COMMENT '表分区规则',
    `sharding_algorithm`     varchar(50)  not null DEFAULT '' COMMENT '分片算法',
    `create_time`            datetime              default CURRENT_TIMESTAMP not null COMMENT '记录创建时间',
    `update_time`            datetime              default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP COMMENT '记录更新时间',
    PRIMARY KEY (`id`)
) comment '迁移数据规则表'