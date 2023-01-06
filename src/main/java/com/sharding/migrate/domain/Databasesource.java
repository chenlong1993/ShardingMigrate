package com.sharding.migrate.domain;

import lombok.Data;

/**
 * @ClassName DataSource
 * @Author ：daiyu
 * @Date 2022-12-25 22:55
 * @Description：
 */
@Data
public class Databasesource {
    private String datasourceId;
    private String url;
    private String userName;
    private String passWord;
    private String code;
    /**
     * 数据源类型，
     * 1:MYSQL，PG
     * 2:Elasticsearch
     * 3:MongoDB
     */
    private Integer databasetype;

}
