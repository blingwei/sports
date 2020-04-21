package com.yw.sports.bean.requestBaen;

import java.util.Date;

public class CreatRecordRequest {
    private String line;
    private float distance;
    private Date creatTime;
    private Date endTime;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
