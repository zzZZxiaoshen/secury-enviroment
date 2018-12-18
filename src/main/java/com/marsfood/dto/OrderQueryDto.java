package com.marsfood.dto;


import com.marsfood.base.BaseQueryParam;

/**
 * 订单查询条件
 * @author huangxingguang
 * @date 2018/12/4
 */
public class OrderQueryDto extends BaseQueryParam {
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 线路分配-省
     */
    private String province;
    /**
     * 线路分配-市
     */
    private String city;
    /**
     * 线路分配-区
     */
    private String area;

    /**
     * 线路分配-线路状态
     */
    private Integer routeStatus;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(Integer routeStatus) {
        this.routeStatus = routeStatus;
    }
}
