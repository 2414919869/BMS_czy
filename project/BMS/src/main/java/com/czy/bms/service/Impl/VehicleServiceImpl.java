package com.czy.bms.service.Impl;

import com.czy.bms.entity.Vehicle;
import com.czy.bms.entity.VehicleExample;
import com.czy.bms.mapper.VehicleMapper;
import com.czy.bms.service.VehicleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Resource
    private VehicleMapper vehicleMapper;

    @Override
    public long countVehicle(){
        VehicleExample vehicleExample = new VehicleExample();
        vehicleExample.createCriteria().andIsDeletedEqualTo(false);
        return vehicleMapper.countByExample(vehicleExample);
    }

    @Override
    public List<Vehicle> getAllVehicles(){
        VehicleExample vehicleExample = new VehicleExample();
        vehicleExample.createCriteria().andIsDeletedEqualTo(false);
        vehicleExample.setOrderByClause("id desc");
        return vehicleMapper.selectByExample(vehicleExample);
    }

    @Override
    public Vehicle getVehiclesByVid(Short vid) {
        VehicleExample vehicleExample = new VehicleExample();
        vehicleExample.createCriteria().andVidEqualTo(vid).andIsDeletedEqualTo(false);
        List<Vehicle> vehicles = vehicleMapper.selectByExample(vehicleExample);
        if(vehicles.size()>0){
            return vehicles.get(0);
        } else {
            return null;
        }

    }

    @Override
    public Boolean deleteByVid(Short vid) {
        Vehicle vehicle=getVehiclesByVid(vid);
        if (vehicle==null||!vehicle.getIsDeleted()){
            return false;
        } else {
            vehicle.setIsDeleted(true);
            vehicleMapper.updateByPrimaryKeySelective(vehicle);
            return true;
        }
    }



}
