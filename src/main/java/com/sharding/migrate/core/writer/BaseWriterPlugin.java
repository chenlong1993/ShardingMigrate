package com.sharding.migrate.core.writer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.sharding.migrate.common.constants.Constants;
import com.sharding.migrate.core.model.MigrateElasticsearchModel;
import com.sharding.migrate.core.model.MigrateMongoDBModel;
import com.sharding.migrate.core.model.MigrateRdbmsModel;
import com.sharding.migrate.core.plugin.MigratePluginInterface;
import com.sharding.migrate.domain.Databasesource;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName BaseWriterPlugin
 * @Author ：daiyu
 * @Date 2023-01-06 22:05
 * @Description：迁移写数据源抽象插件，实现接口的方法供子类重写
 */
public abstract class BaseWriterPlugin implements MigratePluginInterface {
    @Override
    public Map<String, Object> buildRdbms(MigrateRdbmsModel plugin) {
        Map<String, Object> writerObj = Maps.newLinkedHashMap();
        writerObj.put("name", getName());

        Map<String, Object> parameterMap = Maps.newLinkedHashMap();
        Databasesource jobDatasource = plugin.getDatabasesource();
        parameterMap.put("username", jobDatasource.getUserName());
        parameterMap.put("password", jobDatasource.getPassWord());
        parameterMap.put("column", plugin.getRdbmsColumns());
        parameterMap.put("preSql", splitSql(plugin.getPreSql()));
        parameterMap.put("postSql", splitSql(plugin.getPostSql()));

        Map<String, Object> connectionObj = Maps.newLinkedHashMap();
        connectionObj.put("table", plugin.getTables());
        connectionObj.put("jdbcUrl", jobDatasource.getUrl());

        parameterMap.put("connection", ImmutableList.of(connectionObj));
        writerObj.put("parameter", parameterMap);

        return writerObj;
    }


    private String[] splitSql(String sql) {
        String[] sqlArr = null;
        if (StringUtils.isNotBlank(sql)) {
            Pattern p = Pattern.compile("\r\n|\r|\n|\n\r");
            Matcher m = p.matcher(sql);
            String sqlStr = m.replaceAll(Constants.STRING_BLANK);
            sqlArr = sqlStr.split(Constants.SPLIT_COLON);
        }
        return sqlArr;
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
