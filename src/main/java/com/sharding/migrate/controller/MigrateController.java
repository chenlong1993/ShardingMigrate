package com.sharding.migrate.controller;

import com.sharding.migrate.datasource.DBContextHolder;
import com.sharding.migrate.domain.TOrder;
import com.sharding.migrate.service.DatabasesourceService;
import com.sharding.migrate.service.TOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
     * @return
     */
    @GetMapping("/test")
    public  String test() throws Exception {

        //切换到数据库dbtest2
        String datasourceId="db2";
        databasesourceService.changeDb(datasourceId);
        List<TOrder> userList= tOrderService.getAllOrder();
        System.out.println(userList.toString());

        //再切换到数据库dbtest3
        databasesourceService.changeDb("db1");
        List<TOrder> userList2= tOrderService.getAllOrder();
        System.out.println(userList2.toString());


        //切回主数据源
        DBContextHolder.clearDataSource();
        return "ok";
    }
}
