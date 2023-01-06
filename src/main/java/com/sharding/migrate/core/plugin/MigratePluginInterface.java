package com.sharding.migrate.core.plugin;

import com.sharding.migrate.core.model.MigrateElasticsearchModel;
import com.sharding.migrate.core.model.MigrateMongoDBModel;
import com.sharding.migrate.core.model.MigrateRdbmsModel;

import java.util.Map;

/**
 * @ClassName BaseMigratePlugin
 * @Author ：daiyu
 * @Date 2023-01-06 22:10
 * @Description：插件基础接口，定义行为接口
 */
public interface MigratePluginInterface {
    /**
     * 获取reader插件名称
     *
     * @return
     */
    String getName();

    /**
     * 构建mysql，pg
     * 实现类内部定义数据库信息等等
     * @param migrateRdbmsModel
     * @return
     */
    Map<String, Object> buildRdbms(MigrateRdbmsModel migrateRdbmsModel);

    /**
     * es json构建
     * 实现类内部定义es信息等等
     * @param migrateElasticsearchModel
     * @return
     */
    Map<String, Object> buildEs(MigrateElasticsearchModel migrateElasticsearchModel);

    /**
     * mongodb json构建
     * 实现类内部定义json信息等等
     * @param migrateMongoDBModel
     * @return
     */
    Map<String, Object> buildMongoDB(MigrateMongoDBModel migrateMongoDBModel);
}
