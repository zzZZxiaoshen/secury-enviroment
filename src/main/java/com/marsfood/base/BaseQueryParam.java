package com.marsfood.base;

import com.marsfood.utils.BeanHelper;

import java.util.Date;

/**
 * 分页参数
 * @author huangxingguang
 * @date 2018/11/21
 */
public abstract class BaseQueryParam {

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
    protected Integer start = 0;
    /**
     * 页大小
     */
    protected Integer limit = 10;

    /**
     * 转换成Clazz类型
     */
    public final <T extends BaseQueryParam> T toClass(Class<T> clazz) {
        return BeanHelper.deepCopy(this, clazz);
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        if (start != null && start > 0) {
            this.start = start;
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (limit != null && limit > 0) {
            this.limit = limit;
        }
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
