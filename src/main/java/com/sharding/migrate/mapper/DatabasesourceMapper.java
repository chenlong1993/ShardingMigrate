package com.sharding.migrate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sharding.migrate.domain.Databasesource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DatabasesourceMapper extends BaseMapper<Databasesource> {
    int insert(Databasesource record);

    int insertSelective(Databasesource record);

    List<Databasesource> selectAll();

}