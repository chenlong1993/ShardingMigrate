package com.sharding.migrate.core.tool;

import com.sharding.migrate.core.enums.DatabaseEnum;
import com.sharding.migrate.core.model.MigrateRdbmsModel;
import com.sharding.migrate.core.reader.BaseReaderPlugin;
import com.sharding.migrate.core.writer.BaseWriterPlugin;
import com.sharding.migrate.domain.Databasesource;
import com.sharding.migrate.domain.dto.RdbmsReaderDto;
import com.sharding.migrate.domain.dto.RdbmsWriterDto;
import com.sharding.migrate.domain.request.MigrateJsonBuildRes;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MigrateInfoProcess
 * @Author ：daiyu
 * @Date 2023-01-06 16:43
 * @Description：迁移信息处理,在这里对迁移的数据进行封装 目前只针对单表到单表，单表到多表的操作
 */
@Setter
@Getter
public class MigrateInfoProcess implements MigrateJsonInterface {
    /**
     * 读取的表
     */
    private String readerTable;
    /**
     * 读取的字段列表
     */
    private List<String> readerColumns;
    /**
     * reader jdbc 数据源
     */
    private Databasesource readerDatasource;
    /**
     * writer jdbc 数据源
     */
    private Databasesource writerDatasource;
    /**
     * 写入的表
     */
    private String writerTable;
    /**
     * 写入的字段列表
     */
    private List<String> writerColumns;

    private Map<String, Object> buildReader;

    private Map<String, Object> buildWriter;

    private RdbmsReaderDto rdbmsReaderDto;

    private RdbmsWriterDto rdbmsWriterDto;

    private BaseReaderPlugin readerPlugin;

    private BaseWriterPlugin writerPlugin;


    public void initReader(MigrateJsonBuildRes res, Databasesource databasesource) {
        this.readerDatasource = databasesource;
        this.readerColumns = res.getReaderColumns();
        this.readerTable = res.getReaderTable();

        Integer datasource = databasesource.getDatabasetype();
        //判断数据源类型
        if (DatabaseEnum.MYSQL.getKey() == datasource) {
            buildReader = buildRdbmsReader();
        }
    }

    public void initWriter(MigrateJsonBuildRes res, Databasesource databasesource) {
        this.readerDatasource = databasesource;
        this.writerColumns = res.getWriterColumns();
        this.writerTable = res.getWriterTable();

        Integer datasource = databasesource.getDatabasetype();
        //判断数据源类型
        if (DatabaseEnum.MYSQL.getKey() == datasource) {
            buildWriter = buildRdbmsWriter();
        }
    }

    @Override
    public Map<String, Object> buildJob() {
        return null;
    }

    @Override
    public Map<String, Object> buildSetting() {
        return null;
    }

    @Override
    public Map<String, Object> buildContent() {
        return null;
    }

    @Override
    public Map<String, Object> buildESReader() {
        return null;
    }

    @Override
    public Map<String, Object> buildEsWriter() {
        return null;
    }

    @Override
    public Map<String, Object> buildMongoDBReader() {
        return null;
    }

    @Override
    public Map<String, Object> buildMongoDBWriter() {
        return null;
    }

    @Override
    public Map<String, Object> buildRdbmsReader() {
        MigrateRdbmsModel rdbmsModel = new MigrateRdbmsModel();
        rdbmsModel.setDatabasesource(readerDatasource);
        rdbmsModel.setTables(readerTable);
        rdbmsModel.setRdbmsColumns(readerColumns);
        rdbmsModel.setSplitPk(rdbmsReaderDto.getReaderSplitPk());
        if (StringUtils.isNotBlank(rdbmsReaderDto.getQuerySql())) {
            rdbmsModel.setQuerySql(rdbmsReaderDto.getQuerySql());
        }
        //where
        if (StringUtils.isNotBlank(rdbmsReaderDto.getWhereParams())) {
            rdbmsModel.setWhereParam(rdbmsReaderDto.getWhereParams());
        }
        return readerPlugin.buildRdbms(rdbmsModel);
    }

    @Override
    public Map<String, Object> buildRdbmsWriter() {
        MigrateRdbmsModel rdbmsModel = new MigrateRdbmsModel();
        rdbmsModel.setDatabasesource(writerDatasource);
        rdbmsModel.setTables(writerTable);
        rdbmsModel.setRdbmsColumns(writerColumns);
        rdbmsModel.setPreSql(rdbmsModel.getPreSql());
        rdbmsModel.setPostSql(rdbmsModel.getPostSql());
        return writerPlugin.buildRdbms(rdbmsModel);
    }
}
