package com.czy.bms.service;

import com.czy.bms.entity.Vehicle;
import com.czy.bms.request.VehicleQueryReq;
import com.czy.bms.request.VehicleSaveReq;

import java.util.List;

public interface VehicleService {
    long countVehicle();
    List<Vehicle> getAllVehicles();
    Vehicle getVehiclesByVid(Short vid);
    Boolean deleteByVid(Short vid);
    Vehicle saveVehicle(VehicleSaveReq vehicleSaveReq);
    Vehicle selectByVid(Short vid);
    List<Vehicle> selectVehicles(VehicleQueryReq vehicleQueryReq);
}
