package com.sharding.migrate.mapper;

import com.sharding.migrate.domain.MigrateDatasource;

public interface MigrateDatasourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MigrateDatasource record);

    int insertSelective(MigrateDatasource record);

    MigrateDatasource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MigrateDatasource record);

    int updateByPrimaryKey(MigrateDatasource record);
}