package com.sharding.migrate.service.impl;

import com.sharding.migrate.core.tool.MigrateInfoProcess;
import com.sharding.migrate.domain.Databasesource;
import com.sharding.migrate.domain.request.MigrateJsonBuildRes;
import com.sharding.migrate.service.MigrateJsonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 迁移json构建实现类
 */
@Service
public class MigrateJsonServiceImpl implements MigrateJsonService {


    @Resource
    private DatabasesourceService databasesourceService;

    @Override
    public String buildJobJson(MigrateJsonBuildRes res) {
        MigrateInfoProcess migrateInfoProcess = new MigrateInfoProcess();
        //获取读数据源
        Databasesource readerDatasource = databasesourceService.getDatasourceById(res.getReaderDatasourceId());
        //封装读数据源json
        migrateInfoProcess.initReader(res, readerDatasource);
        //获取写数据源
        Databasesource writerDatasource = databasesourceService.getDatasourceById(res.getWriterDatasourceId());
        //封装写数据源json
        migrateInfoProcess.initWriter(res, writerDatasource);
        return null;
    }
}