package com.sharding.migrate.core.tool;

import java.util.Map;


/**
 * @ClassName MigrateJsonInterface
 * @Author ：daiyu
 * @Date 2023-01-06 16:45
 * @Description：迁移信息处理
 */
public interface MigrateJsonInterface {

    Map<String, Object> buildJob();

    Map<String, Object> buildSetting();

    Map<String, Object> buildContent();

    Map<String, Object> buildESReader();

    Map<String, Object> buildEsWriter();

    Map<String, Object> buildMongoDBReader();

    Map<String, Object> buildMongoDBWriter();

    Map<String, Object> buildRdbmsReader();

    Map<String, Object> buildRdbmsWriter();
}
