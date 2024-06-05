package com.czy.bms.service.Impl;

import com.czy.bms.mapper.VehicleMapper;
import com.czy.bms.service.VehicleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Resource
    private VehicleMapper vehicleMapper;

    @Override
    public int countVehicle(){
        return vehicleMapper.countVehicle();
    }
}
