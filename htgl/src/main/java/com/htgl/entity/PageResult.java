package com.htgl.entity;

import java.util.List;

/**
 * 分页查询结果封装
 */
public class PageResult {
    private Integer count;
    private List rows;

    public PageResult() {
    }

    public PageResult(Integer count, List rows) {
        this.count = count;
        this.rows = rows;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
