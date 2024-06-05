package com.czy.bms.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VehicleSaveReq {
    @NotNull(message = "【车架编号】不能为空！")
    private Short vid;
    @NotNull(message = "【电池类型】不能为空！")
    private String battery;
    @NotNull(message = "【总里程】不能为空！")
    private Integer mileage;
    @NotNull(message = "【电池健康状态】不能为空！")
    private Byte batteryHealth;
}
