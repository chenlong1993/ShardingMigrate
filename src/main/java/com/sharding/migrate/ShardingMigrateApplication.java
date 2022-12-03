package com.sharding.migrate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author apple
 */
@MapperScan(basePackages = {"com.sharding.migrate.mapper"})
@SpringBootApplication
public class ShardingMigrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingMigrateApplication.class, args);
    }

}
