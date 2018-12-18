package com.marsfood.base;

import com.marsfood.utils.BeanHelper;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽象父类
 * @author huangxingguang
 * @date 2018/11/21
 */
public abstract class AbstractBase implements Serializable {

    private static final long serialVersionUID = -4022313520878257265L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date gmtCreated;
    /**
     * 最后修改时间
     */
    private Date gmtModified;

    /**
     * 转换成Clazz类型
     */
    public final <T extends AbstractBase> T toClass(Class<T> clazz) {
        return BeanHelper.deepCopy(this, clazz);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
