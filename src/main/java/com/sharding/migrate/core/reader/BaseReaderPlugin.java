package com.sharding.migrate.core.reader;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.sharding.migrate.core.model.MigrateElasticsearchModel;
import com.sharding.migrate.core.model.MigrateMongoDBModel;
import com.sharding.migrate.core.model.MigrateRdbmsModel;
import com.sharding.migrate.core.plugin.MigratePluginInterface;
import com.sharding.migrate.domain.Databasesource;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @ClassName BaseReaderPlugin
 * @Author ：daiyu
 * @Date 2023-01-06 22:05
 * @Description：迁移读数据源抽象插件，实现接口的方法供子类重写
 */
public abstract class BaseReaderPlugin implements MigratePluginInterface {

    @Override
    public Map<String, Object> buildRdbms(MigrateRdbmsModel plugin) {
        //构建
        Map<String, Object> readerObj = Maps.newLinkedHashMap();
        readerObj.put("name", getName());
        Map<String, Object> parameterMap = Maps.newLinkedHashMap();
        Map<String, Object> connectionObj = Maps.newLinkedHashMap();

        Databasesource jobDatasource = plugin.getDatabasesource();
        parameterMap.put("username", jobDatasource.getUserName());
        parameterMap.put("password", jobDatasource.getPassWord());

        //判断是否是 querySql
        if (StringUtils.isNotBlank(plugin.getQuerySql())) {
            connectionObj.put("querySql", ImmutableList.of(plugin.getQuerySql()));
        } else {
            parameterMap.put("column", plugin.getRdbmsColumns());
            //判断是否有where
            if (StringUtils.isNotBlank(plugin.getWhereParam())) {
                parameterMap.put("where", plugin.getWhereParam());
            }
            connectionObj.put("table", plugin.getTables());
        }
        parameterMap.put("splitPk",plugin.getSplitPk());
        connectionObj.put("jdbcUrl", ImmutableList.of(jobDatasource.getUrl()));

        parameterMap.put("connection", ImmutableList.of(connectionObj));

        readerObj.put("parameter", parameterMap);

        return readerObj;
    }

    @Override
    public Map<String, Object> buildEs(MigrateElasticsearchModel migrateElasticsearchModel) {
        return null;
    }

    @Override
    public Map<String, Object> buildMongoDB(MigrateMongoDBModel migrateMongoDBModel) {
        return null;
    }
}
