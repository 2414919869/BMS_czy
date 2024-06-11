package com.czy.bms.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserInfoQueryVehiclesReq {
    @NotNull
    private Long uid;
    private int vehicleId;
    private Short vid;
    private String battery;
    private Integer mileage;
    private Byte batteryHealth;
}
