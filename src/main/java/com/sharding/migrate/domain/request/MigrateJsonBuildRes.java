package com.sharding.migrate.domain.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 构建json dto
 *
 * @author jingwk
 * @ClassName DataxJsonDto
 * @Version 2.1.1
 * @since 2020/03/14 07:15
 */
@Getter
@Setter
public class MigrateJsonBuildRes implements Serializable {

    private Long readerDatasourceId;

    private String readerTable;

    private List<String> readerColumns;

    private Long writerDatasourceId;

    private String writerTable;

    private List<String> writerColumns;

//    private HiveReaderDto hiveReader;
//
//    private HiveWriterDto hiveWriter;
//
//    private HbaseReaderDto hbaseReader;
//
//    private HbaseWriterDto hbaseWriter;
//
//    private RdbmsReaderDto rdbmsReader;
//
//    private RdbmsWriterDto rdbmsWriter;
//
//    private MongoDBReaderDto mongoDBReader;
//
//    private MongoDBWriterDto mongoDBWriter;
}
