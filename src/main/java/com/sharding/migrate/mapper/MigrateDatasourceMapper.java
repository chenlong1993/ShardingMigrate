package com.sharding.migrate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sharding.migrate.domain.MigrateDatasource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MigrateDatasourceMapper  extends BaseMapper<MigrateDatasource> {

}