package com.sharding.migrate.controller;

import com.sharding.migrate.domain.Databasesource;
import com.sharding.migrate.service.impl.DatabasesourceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DatabaseController
 * @Author ：daiyu
 * @Date 2023-01-08 21:56
 * @Description：数据库查询
 */
@RestController
@RequestMapping("/source")
public class DatabaseController {
    @Resource
    private DatabasesourceService databasesourceService;

    @PostMapping("/getAllDatasource")
    public List<Databasesource> getAllDatasource(){
        return databasesourceService.getAllDatasource();
    }

}
