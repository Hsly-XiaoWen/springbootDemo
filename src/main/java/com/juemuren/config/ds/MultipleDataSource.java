package com.juemuren.config.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 肖文 on 2018/11/30.
 */
public class MultipleDataSource {

    private Logger logger = LoggerFactory.getLogger(MultipleDataSource.class);

    private String defaultDataSource;
    private Set<String> dataSources;

    public String getDefaultDataSource() {
        return defaultDataSource;
    }

    public void setDefaultDataSource(String defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }

    public Set<String> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Set<String> dataSources) {
        this.dataSources = dataSources;
    }

    /**
     * list中的第一个为默认数据源
     * @param dataSources
     */
    public MultipleDataSource(List<String> dataSources) {

        if(dataSources == null || dataSources.isEmpty()) {
            throw new RuntimeException("MultipleDataSource dataSources cannot be empty!");
        }

        this.dataSources = new HashSet<>(dataSources);
        this.defaultDataSource = dataSources.get(0);
        DataSourceHolder.defaultDataSource4Record = this.defaultDataSource;
    }

}
