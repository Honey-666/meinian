package com.atguigu.pojo;

import java.io.Serializable;

public class TravelGroupTravelItemKey implements Serializable {
    private Integer travelgroupId;

    private Integer travelitemId;

    private static final long serialVersionUID = 1L;

    public Integer getTravelgroupId() {
        return travelgroupId;
    }

    public void setTravelgroupId(Integer travelgroupId) {
        this.travelgroupId = travelgroupId;
    }

    public Integer getTravelitemId() {
        return travelitemId;
    }

    public void setTravelitemId(Integer travelitemId) {
        this.travelitemId = travelitemId;
    }
}