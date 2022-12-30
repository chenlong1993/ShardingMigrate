package com.sharding.migrate.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sharding.migrate.domain.Databasesource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.sharding.migrate.mapper.DatabasesourceMapper;

import java.util.List;

@Service
public class DatabasesourceService  extends AbstractCheckoutDataSource  {

    @Resource
    private DatabasesourceMapper databasesourceMapper;





}
