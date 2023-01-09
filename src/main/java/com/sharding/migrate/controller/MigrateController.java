package com.sharding.migrate.controller;

import com.sharding.migrate.service.impl.DatabasesourceService;
import com.sharding.migrate.service.impl.TOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName MigrateController
 * @Author ：daiyu
 * @Date 2022-12-05 20:29
 * @Description：迁移逻辑配置
 */
@RestController
public class MigrateController {
    @Resource
    private DatabasesourceService databasesourceService;
    @Resource
    private TOrderService tOrderService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("/test")
    public String test() throws Exception {


        return "这里是一条响应数据";
    }
}
