package com.sharding.migrate.mapper;

import com.sharding.migrate.domain.MigrateRule;

public interface MigrateRuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MigrateRule record);

    int insertSelective(MigrateRule record);

    MigrateRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MigrateRule record);

    int updateByPrimaryKey(MigrateRule record);
}