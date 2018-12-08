package com.juemuren.config.ds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by 肖文 on 2018/11/30.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }
}


