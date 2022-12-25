package com.sharding.migrate.domain;

import lombok.Data;

/**
 * @ClassName DataSource
 * @Author ：daiyu
 * @Date 2022-12-25 22:55
 * @Description：
 */
@Data
public class DataSource {
    String datasourceId;
    String url;
    String userName;
    String passWord;
    String code;
    String databasetype;
}
