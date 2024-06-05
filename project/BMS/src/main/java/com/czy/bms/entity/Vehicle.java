package com.czy.bms.entity;

import java.util.Date;

public class Vehicle {
    private Long id;

    private Short vid;

    private String battery;

    private Integer mileage;

    private Byte batteryHealth;

    private Date createdTime;

    private Date updatedTime;

    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getVid() {
        return vid;
    }

    public void setVid(Short vid) {
        this.vid = vid;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Byte getBatteryHealth() {
        return batteryHealth;
    }

    public void setBatteryHealth(Byte batteryHealth) {
        this.batteryHealth = batteryHealth;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", vid=").append(vid);
        sb.append(", battery=").append(battery);
        sb.append(", mileage=").append(mileage);
        sb.append(", batteryHealth=").append(batteryHealth);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append("]");
        return sb.toString();
    }
}