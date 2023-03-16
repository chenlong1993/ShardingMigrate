package com.sharding.migrate.controller;

import com.sharding.migrate.domain.database.ColumnInfo;
import com.sharding.migrate.service.MetadataService;
import com.sharding.migrate.service.MigrateDatasourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MatedataController
 * @Author ：daiyu
 * @Date 2023-03-16 10:31
 * @Description：
 */
@RestController
@RequestMapping("/matedata")
public class MatedataController {

    @Resource
    private MetadataService metadataService;

    @GetMapping("/getTableBySchema")
    @ApiOperation("获得数据库下的所有table")
    public List<String> getTableBySchema(String schemaName) throws SQLException {
        return null;
    }

    @GetMapping("/getColumnsByTable")
    @ApiOperation("获得表下的所有列信息")
    public List<ColumnInfo> getColumnsByTable(String tableName, String datasourceId) throws SQLException {
        return metadataService.getColumnsByTable(tableName, datasourceId);
    }

}
