package com.marsfood.entity;

import com.marsfood.utils.Constant;

import java.util.Date;

/**
 * 分页参数
 * @author huangxingguang
 * @date 2018/10/30
 */
public class QueryParam {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 开始索引
     */
    private Integer start = Constant.DEFAULT_PAGE_START;
    /**
     * 页大小
     */
    private Integer limit = Constant.DEFAULT_PAGE_LIMIT;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        if (start == null || start < 0) {
            start = Constant.DEFAULT_PAGE_START;
        }
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (limit == null || limit < 0) {
            limit = Constant.DEFAULT_PAGE_LIMIT;
        }
        this.limit = limit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
