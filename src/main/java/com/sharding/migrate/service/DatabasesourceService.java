package com.sharding.migrate.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sharding.migrate.config.DynamicDataSource;
import com.sharding.migrate.datasource.DBContextHolder;
import com.sharding.migrate.domain.Databasesource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.sharding.migrate.mapper.DatabasesourceMapper;

import java.util.List;

@Service
public class DatabasesourceService  extends ServiceImpl<DatabasesourceMapper, Databasesource> {

    @Resource
    private DatabasesourceMapper databasesourceMapper;

    @Resource
    private DynamicDataSource dynamicDataSource;


    public int insert(Databasesource record) {
        return databasesourceMapper.insert(record);
    }


    public int insertSelective(Databasesource record) {
        return databasesourceMapper.insertSelective(record);
    }


    public List<Databasesource> getDatasource() {
        List<Databasesource> databasesources = databasesourceMapper.selectAll();
        return databasesources;
    }

    public boolean changeDb(String datasourceId) throws Exception {
        //默认切换到主数据源,进行整体资源的查找
        DBContextHolder.clearDataSource();

        List<Databasesource> dataSourcesList = getDatasource();

        for (Databasesource databasesource : dataSourcesList) {
            if (databasesource.getDatasourceId().equals(datasourceId)) {
                System.out.println("需要使用的的数据源已经找到,datasourceId是：" + databasesource.getDatasourceId());
                //创建数据源连接&检查 若存在则不需重新创建
                dynamicDataSource.createDataSourceWithCheck(databasesource);
                //切换到该数据源
                DBContextHolder.setDataSource(databasesource.getDatasourceId());
                return true;
            }
        }
        return false;

    }

}
