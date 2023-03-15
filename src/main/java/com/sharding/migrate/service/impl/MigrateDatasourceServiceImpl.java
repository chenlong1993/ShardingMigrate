package com.sharding.migrate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sharding.migrate.domain.response.MigrateDatasourceReq;
import com.sharding.migrate.service.MigrateDatasourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.sharding.migrate.mapper.MigrateDatasourceMapper;
import com.sharding.migrate.domain.MigrateDatasource;

import java.util.ArrayList;
import java.util.List;

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
}
