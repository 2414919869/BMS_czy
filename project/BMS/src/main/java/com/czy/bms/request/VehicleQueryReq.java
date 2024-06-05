package com.czy.bms.request;

import lombok.Data;

@Data
public class VehicleQueryReq {
    private Short vid;
    private String battery;
    private Integer mileage;
    private Byte batteryHealth;
}
