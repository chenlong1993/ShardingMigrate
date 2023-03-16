package com.sharding.migrate.core.enums;

import java.util.Arrays;

public enum DatabaseEnum {
    MYSQL(1, "mysql"),
    POSTGRESQL(2, "postgresql"),
    ELASTICSEARCH(3, "elasticsearch"),
    MONGODB(4, "mongodb");

    int key;
    String name;

    DatabaseEnum(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public static DatabaseEnum getNameByKey(int key) {
        return Arrays.stream(DatabaseEnum.values()).filter(e -> e.getKey() == key).findAny().orElseThrow(RuntimeException::new);
    }
}
