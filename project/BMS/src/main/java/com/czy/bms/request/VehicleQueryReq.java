package com.czy.bms.request;

import lombok.Data;

@Data
public class VehicleQueryReq {
    private int vehicleId;
    private Short vid;
    private String battery;
    private Integer mileage;
    private Byte batteryHealth;
}
