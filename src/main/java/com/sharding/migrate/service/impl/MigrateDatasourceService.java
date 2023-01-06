package com.sharding.migrate.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.sharding.migrate.mapper.MigrateDatasourceMapper;
import com.sharding.migrate.domain.MigrateDatasource;

@Service
public class MigrateDatasourceService{

    @Resource
    private MigrateDatasourceMapper migrateDatasourceMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return migrateDatasourceMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(MigrateDatasource record) {
        return migrateDatasourceMapper.insert(record);
    }

    
    public int insertSelective(MigrateDatasource record) {
        return migrateDatasourceMapper.insertSelective(record);
    }

    
    public MigrateDatasource selectByPrimaryKey(Long id) {
        return migrateDatasourceMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(MigrateDatasource record) {
        return migrateDatasourceMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(MigrateDatasource record) {
        return migrateDatasourceMapper.updateByPrimaryKey(record);
    }

}
