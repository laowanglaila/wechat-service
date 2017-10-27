package com.hualala.app.wechat.config.datasource;

/**
 * Created by renjianfei on 2017/8/28.
 */
public class DataSourceTypeManager {
    private static final ThreadLocal<DataSources> dataSourceTypes = new ThreadLocal<DataSources>(){
        @Override
        protected DataSources initialValue(){
            return DataSources.SHOP;
        }
    };

    public static DataSources get(){
        return dataSourceTypes.get();
    }

    public static void set(DataSources dataSourceType){
        dataSourceTypes.set(dataSourceType);
    }

    public static void reset(){
        dataSourceTypes.set(DataSources.SHOP);
    }
}
