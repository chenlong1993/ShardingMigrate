package com.sharding.migrate.core.datasource;

/**
 * @ClassName JobDataSourceHolder
 * @Author ：daiyu
 * @Date 2023-01-09 17:29
 * @Description：数据源临时存储
 */
public class JobDataSourceHolder {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String getDataSourceName(){
        return threadLocal.get();
    }

    public static void setDataSourceName(String dataSourceName){
        threadLocal.set(dataSourceName);
    }

    public static void removeDataSourceName(){
        threadLocal.remove();
    }
}
