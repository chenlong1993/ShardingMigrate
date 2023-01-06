package com.sharding.migrate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.sharding.migrate.domain.TOrder;
import com.sharding.migrate.mapper.TOrderMapper;

import java.util.List;

@Service
public class TOrderService extends ServiceImpl<TOrderMapper,TOrder> {

    @Resource
    private TOrderMapper tOrderMapper;

    
    public List<TOrder> getAllOrder(){
        return tOrderMapper.selectList();
    }
}
