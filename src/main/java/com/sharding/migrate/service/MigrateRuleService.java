package com.sharding.migrate.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.sharding.migrate.mapper.MigrateRuleMapper;
import com.sharding.migrate.domain.MigrateRule;
@Service
public class MigrateRuleService{

    @Resource
    private MigrateRuleMapper migrateRuleMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return migrateRuleMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(MigrateRule record) {
        return migrateRuleMapper.insert(record);
    }

    
    public int insertSelective(MigrateRule record) {
        return migrateRuleMapper.insertSelective(record);
    }

    
    public MigrateRule selectByPrimaryKey(Long id) {
        return migrateRuleMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(MigrateRule record) {
        return migrateRuleMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(MigrateRule record) {
        return migrateRuleMapper.updateByPrimaryKey(record);
    }

}
