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
        log.info("项目启动，正在加载数据库信息");
        return dataSourcesMapper.selectAll();
    }

    public Databasesource getDatasourceById(Long id){
        return dataSourcesMapper.selectById(id);
    }

}
