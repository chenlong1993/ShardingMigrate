package com.sharding.migrate.config;

import com.sharding.migrate.domain.Databasesource;
import com.sharding.migrate.mapper.DataSourcesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class DatabasesourceConfig {

    @Resource
    private DataSourcesMapper dataSourcesMapper;

    @PostConstruct
    public List<Databasesource> getAllDatasource(){
        log.info("项目启动，正在加载数据库信息");
        return dataSourcesMapper.selectAll();
    }

}
