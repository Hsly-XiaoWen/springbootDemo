package com.tuandai.dto;

import java.util.List;

/**
 * Created by 肖文 on 2018/5/14
 * 封装的页面类
 */
public class Page<T> {

    private int count;//数据量
    private List<T> data;//数据

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
