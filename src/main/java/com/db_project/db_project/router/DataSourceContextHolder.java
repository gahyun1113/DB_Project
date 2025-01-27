package com.db_project.db_project.router;

import com.db_project.db_project.enums.DataSourceType;

public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    public static void setDataSourceType(DataSourceType dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static DataSourceType getDataSourceType() {
        return contextHolder.get() == null ? DataSourceType.MASTER : contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}

