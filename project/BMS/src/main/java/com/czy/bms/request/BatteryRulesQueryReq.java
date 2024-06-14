package com.czy.bms.request;

import lombok.Data;

@Data
public class BatteryRulesQueryReq {
    private Integer rid;

    private Short vid;

    private String ruleId;

    private String name;

    private String battery;

    private String level;

}
