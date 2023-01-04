package com.sharding.migrate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sharding.migrate.domain.Databasesource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataSourcesMapper extends BaseMapper<Databasesource> {
    /**
     * 插入数据源
     *
     * @param datasource 数据源信息
     * @return 成功条数
     */
    int insertDataSource(Databasesource datasource);


    /**
     * 查询出所有的数据源
     *
     * @return 数据源集合
     */
    List<Databasesource> selectAll();

}