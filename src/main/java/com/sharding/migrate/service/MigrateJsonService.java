package com.sharding.migrate.service;

import com.sharding.migrate.domain.request.MigrateJsonBuildRes;

/**
 * @ClassName MigrateJsonService
 * @Author ：daiyu
 * @Date 2023-01-06 17:01
 * @Description：前端请求封装迁移json
 */
public interface MigrateJsonService {

    /**
     * build migrate json
     *
     * @param dto
     * @return
     */
    String buildJobJson(MigrateJsonBuildRes res);
}
