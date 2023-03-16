package com.sharding.migrate.controller;

import com.sharding.migrate.domain.response.MigrateDatasourceReq;
import com.sharding.migrate.service.MigrateDatasourceService;
import com.sharding.migrate.service.impl.TOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MigrateController
 * @Author ：daiyu
 * @Date 2022-12-05 20:29
 * @Description：迁移逻辑配置
 */
@Api(tags = "迁移逻辑配置")
@RestController
@RequestMapping("/migrate")
public class MigrateController {
    @Resource
    private MigrateDatasourceService migrateDatasourceService;

    @Resource
    private TOrderService tOrderService;

    /**
     * 查询所有
     * search all
     *
     * @return
     */
    @ApiOperation("查询所有")
    @PostMapping("/getInfo")
    public List<MigrateDatasourceReq> getMigrateInfo() {
        return migrateDatasourceService.selectList();
    }

    @PostMapping("/addInfo")
    public Boolean addMigrateInfo(@RequestBody MigrateDatasourceReq migrateDatasourceReq) {
        return migrateDatasourceService.addMigrateInfo(migrateDatasourceReq);
    }

    @PostMapping("/deleteInfo")
    public String deleteMigrateInfo() {


        return "这里是一条响应数据";
    }

    @PostMapping("/updateInfo")
    public String updateMigrateInfo() {


        return "这里是一条响应数据";
    }
}
