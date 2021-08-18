package com.atguigu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Integer id;

    private Integer memberId;

    private Date orderdate;

    private String ordertype;

    private String orderstatus;

    private Integer setmealId;

    private static final long serialVersionUID = 1L;

    public Order(Integer memberId, Date orderdate, String ordertype, String orderstatus, Integer setmealId) {
        this.memberId = memberId;
        this.orderdate = orderdate;
        this.ordertype = ordertype;
        this.orderstatus = orderstatus;
        this.setmealId = setmealId;
    }

    public Order() {
    }

    public Order(Integer id, Integer memberId, Date orderdate, String ordertype, String orderstatus, Integer setmealId) {
        this.id = id;
        this.memberId = memberId;
        this.orderdate = orderdate;
        this.ordertype = ordertype;
        this.orderstatus = orderstatus;
        this.setmealId = setmealId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype == null ? null : ordertype.trim();
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public Integer getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(Integer setmealId) {
        this.setmealId = setmealId;
    }
}