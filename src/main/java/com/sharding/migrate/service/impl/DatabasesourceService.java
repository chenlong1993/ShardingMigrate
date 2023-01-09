package com.sharding.migrate.service.impl;

import com.sharding.migrate.domain.Databasesource;
import com.sharding.migrate.mapper.DataSourcesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Slf4j
@Service
public class DatabasesourceService   {

    @Resource
    private DataSourcesMapper dataSourcesMapper;

    public List<Databasesource> getAllDatasource(){
        return dataSourcesMapper.selectAll();
    }

    public Databasesource getDatasourceById(Long id){
        return dataSourcesMapper.selectById(id);
    }

}
