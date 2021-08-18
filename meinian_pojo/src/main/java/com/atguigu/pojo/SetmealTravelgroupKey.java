package com.atguigu.pojo;

import java.io.Serializable;

public class SetmealTravelgroupKey implements Serializable {
    private Integer setmealId;

    private Integer travelgroupId;

    private static final long serialVersionUID = 1L;

    public Integer getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(Integer setmealId) {
        this.setmealId = setmealId;
    }

    public Integer getTravelgroupId() {
        return travelgroupId;
    }

    public void setTravelgroupId(Integer travelgroupId) {
        this.travelgroupId = travelgroupId;
    }
}