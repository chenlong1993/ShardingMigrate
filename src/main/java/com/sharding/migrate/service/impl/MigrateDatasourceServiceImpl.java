package com.sharding.migrate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.google.common.collect.Lists;
import com.sharding.migrate.core.datasource.query.BaseQuery;
import com.sharding.migrate.core.datasource.query.QueryFactory;
import com.sharding.migrate.domain.response.MigrateDatasourceReq;
import com.sharding.migrate.service.MigrateDatasourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.sharding.migrate.mapper.MigrateDatasourceMapper;
import com.sharding.migrate.domain.MigrateDatasource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MigrateDatasourceServiceImpl implements MigrateDatasourceService {

    @Resource
    private MigrateDatasourceMapper migrateDatasourceMapper;


    @Override
    public List<MigrateDatasourceReq> selectList() {
        LambdaQueryWrapper<MigrateDatasource> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<MigrateDatasourceReq> migrateDatasourceReqList = new ArrayList<>();
        List<MigrateDatasource> migrateDatasources = migrateDatasourceMapper.selectList(lambdaQueryWrapper);

        migrateDatasources.forEach(e -> {
            MigrateDatasourceReq migrateDatasourceReq = new MigrateDatasourceReq();
            BeanUtils.copyProperties(e, migrateDatasourceReq);
            migrateDatasourceReqList.add(migrateDatasourceReq);
        });
        return migrateDatasourceReqList;
    }

    @Override
    public Boolean addMigrateInfo(MigrateDatasourceReq migrateDatasourceReq) {
        MigrateDatasource migrateDatasource = new MigrateDatasource();
        BeanUtils.copyProperties(migrateDatasourceReq, migrateDatasource);
        return migrateDatasourceMapper.insert(migrateDatasource) > 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<String> getColumnsByQuerySql(Long datasourceId, String querySql) throws SQLException {
        //获取数据源对象
        MigrateDatasource jdbcDatasource = migrateDatasourceMapper.selectById(datasourceId);
        //queryTool组装
        if (Objects.isNull(jdbcDatasource)) {
            return Lists.newArrayList();
        }
        BaseQuery queryTool = QueryFactory.getByDbType(jdbcDatasource);
        return queryTool.getColumnsByQuerySql(querySql);
    }

    @Override
    public List<String> getColumnById(Long id) {

        return null;
    }
}
