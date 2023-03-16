package com.sharding.migrate.service;

import com.sharding.migrate.domain.database.ColumnInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MetadataService
 * @Author ：daiyu
 * @Date 2023-03-16 13:29
 * @Description：元数据接口
 */
public interface MetadataService {


    /**
     * 获取schema下的所有表
     *
     * @param schemaName schemaName
     * @return 表集合
     */
    List<String> getTableBySchema(String schemaName, String datasourceId);

    /**
     * 获取table下的所有列
     *
     * @param tableName    tableName
     * @param datasourceId tableName
     * @return 列集合
     */
    List<ColumnInfo> getColumnsByTable(String tableName, String datasourceId);

    /**
     * 获取table下的主键
     *
     * @param tableName tableName
     * @return 列集合
     */
    List<String> getPrimaryKeyByTable(String tableName, String datasourceId) throws SQLException;
}
