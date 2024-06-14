package com.czy.bms.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BatteryRulesSaveReq {
    @NotNull(message = "【车架编号】不能为空！")
    private Short vid;
    @NotNull(message = "【规则编号】不能为空！")
    private String ruleId;
    @NotNull(message = "【规则名称】不能为空！")
    private String name;
    @NotNull(message = "【电池类型】不能为空！")
    private String battery;

}
