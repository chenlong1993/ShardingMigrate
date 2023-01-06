package com.sharding.migrate.core.model;

import com.sharding.migrate.domain.Databasesource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MigrateElasticsearchModel
 * @Author ：daiyu
 * @Date 2023-01-06 22:05
 * @Description：用于传参，构建json
 */
@Getter
@Setter
public class MigrateElasticsearchModel {

    /**
     * es列名
     */
    private List<Map<String,Object>> columns;

    /**
     * 数据源信息
     */
    private Databasesource databasesource;

    private String readerPath;

    private String readerDefaultFS;

    private String readerFileType;

    private String readerFieldDelimiter;

    private String writerDefaultFS;

    private String writerFileType;

    private String writerPath;

    private String writerFileName;

    private String writeMode;

    private String writeFieldDelimiter;

    private Boolean skipHeader;
}
