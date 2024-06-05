package com.czy.bms.service;

import com.czy.bms.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    long countVehicle();
    List<Vehicle> getAllVehicles();
    Vehicle getVehiclesByVid(Short vid);
    Boolean deleteByVid(Short vid);
}
