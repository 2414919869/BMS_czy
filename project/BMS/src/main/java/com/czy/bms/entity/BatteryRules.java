package com.czy.bms.entity;

import java.util.Date;

public class BatteryRules {
    private Integer rid;

    private Short vid;

    private String ruleId;

    private String name;

    private String battery;

    private Long mx;

    private Long mi;

    private Long ix;

    private Long ii;

    private String level;

    private Date createdTime;

    private Date updatedTime;

    private Boolean isDeleted;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Short getVid() {
        return vid;
    }

    public void setVid(Short vid) {
        this.vid = vid;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public Long getMx() {
        return mx;
    }

    public void setMx(Long mx) {
        this.mx = mx;
    }

    public Long getMi() {
        return mi;
    }

    public void setMi(Long mi) {
        this.mi = mi;
    }

    public Long getIx() {
        return ix;
    }

    public void setIx(Long ix) {
        this.ix = ix;
    }

    public Long getIi() {
        return ii;
    }

    public void setIi(Long ii) {
        this.ii = ii;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
        sb.append(", rid=").append(rid);
        sb.append(", vid=").append(vid);
        sb.append(", ruleId=").append(ruleId);
        sb.append(", name=").append(name);
        sb.append(", battery=").append(battery);
        sb.append(", mx=").append(mx);
        sb.append(", mi=").append(mi);
        sb.append(", ix=").append(ix);
        sb.append(", ii=").append(ii);
        sb.append(", level=").append(level);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append("]");
        return sb.toString();
    }
}