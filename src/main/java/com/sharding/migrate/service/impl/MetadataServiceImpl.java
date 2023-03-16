package com.sharding.migrate.service.impl;

import com.google.common.collect.Lists;
import com.sharding.migrate.core.datasource.query.BaseQuery;
import com.sharding.migrate.core.datasource.query.QueryFactory;
import com.sharding.migrate.domain.MigrateDatasource;
import com.sharding.migrate.domain.database.ColumnInfo;
import com.sharding.migrate.mapper.MigrateDatasourceMapper;
import com.sharding.migrate.service.MetadataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName MetadataServiceImpl
 * @Author ：daiyu
 * @Date 2023-03-16 13:29
 * @Description：元数据接口实现类
 */
@Service
public class MetadataServiceImpl implements MetadataService {

    @Resource
    private MigrateDatasourceMapper migrateDatasourceMapper;

    @Override
    public List<String> getTableBySchema(String schemaName, String datasourceId) {
        //获取数据源对象
        MigrateDatasource jdbcDatasource = migrateDatasourceMapper.selectById(datasourceId);
        //queryTool组装
        if (Objects.isNull(jdbcDatasource)) {
            return Lists.newArrayList();
        }
        BaseQuery queryTool = QueryFactory.getByDbType(jdbcDatasource);
        return queryTool.getTableNames(schemaName);
    }

    @Override
    public List<ColumnInfo> getColumnsByTable(String tableName, String datasourceId) {
        //获取数据源对象
        MigrateDatasource jdbcDatasource = migrateDatasourceMapper.selectById(datasourceId);
        //queryTool组装
        if (Objects.isNull(jdbcDatasource)) {
            return Lists.newArrayList();
        }
        BaseQuery queryTool = QueryFactory.getByDbType(jdbcDatasource);
        return queryTool.getColumns(tableName);
    }

    @Override
    public List<String> getPrimaryKeyByTable(String tableName, String datasourceId) throws SQLException {
        //获取数据源对象
        MigrateDatasource jdbcDatasource = migrateDatasourceMapper.selectById(datasourceId);
        //queryTool组装
        if (Objects.isNull(jdbcDatasource)) {
            return Lists.newArrayList();
        }
        BaseQuery queryTool = QueryFactory.getByDbType(jdbcDatasource);
        return queryTool.getPrimaryKey(queryTool.getCurrentSchema(), tableName);
    }
}
