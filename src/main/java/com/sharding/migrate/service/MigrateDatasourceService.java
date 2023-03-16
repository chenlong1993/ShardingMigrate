package com.sharding.migrate.service;

import com.sharding.migrate.domain.MigrateDatasource;
import com.sharding.migrate.domain.response.MigrateDatasourceReq;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MigrateDatasourceService
 * @Author ：daiyu
 * @Date 2023-03-15 18:59
 * @Description：
 */
public interface MigrateDatasourceService {

    List<MigrateDatasourceReq> selectList();

    Boolean addMigrateInfo(MigrateDatasourceReq migrateDatasourceReq);

    List<String> getColumnsByQuerySql(Long datasourceId, String querySql) throws SQLException;

    List<String> getColumnById(Long id);






}
