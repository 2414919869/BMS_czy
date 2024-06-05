package com.czy.bms.service.Impl;

import com.czy.bms.entity.Vehicle;
import com.czy.bms.entity.VehicleExample;
import com.czy.bms.mapper.VehicleMapper;
import com.czy.bms.request.VehicleQueryReq;
import com.czy.bms.request.VehicleSaveReq;
import com.czy.bms.service.VehicleService;
import com.czy.bms.util.RandomIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Resource
    private VehicleMapper vehicleMapper;

    @Override
    public long countVehicle() {
        VehicleExample vehicleExample = new VehicleExample();
        vehicleExample.createCriteria().andIsDeletedEqualTo(false);
        return vehicleMapper.countByExample(vehicleExample);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
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
        if (vehicles.size() > 0) {
            return vehicles.get(0);
        } else {
            return null;
        }

    }

    @Override
    public Boolean deleteByVid(Short vid) {
        Vehicle vehicle = getVehiclesByVid(vid);
        if (vehicle != null && vehicle.getIsDeleted() == false) {
            vehicle.setIsDeleted(true);
            vehicle.setUpdatedTime(new Date());
            vehicleMapper.updateByPrimaryKeySelective(vehicle);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Vehicle saveVehicle(VehicleSaveReq vehicleSaveReq) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleSaveReq, vehicle);
        vehicle.setId(new RandomIdUtil().getRandomId());
        vehicle.setCreatedTime(new Date());
        vehicle.setUpdatedTime(new Date());
        vehicle.setIsDeleted(false);
        vehicleMapper.insert(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle selectVehicle(VehicleQueryReq vehicleQueryReq) {
        VehicleExample vehicleExample = new VehicleExample();
        if (vehicleQueryReq.getVid() != null) {
            vehicleExample.createCriteria().andVidEqualTo(vehicleQueryReq.getVid());
        }
        if (vehicleQueryReq.getBattery() != null) {
            vehicleExample.createCriteria().andBatteryEqualTo(vehicleQueryReq.getBattery());
        }
        if (vehicleQueryReq.getMileage() != null) {
            vehicleExample.createCriteria().andMileageEqualTo(vehicleQueryReq.getMileage());
        }
        if (vehicleQueryReq.getBatteryHealth() != null) {
            vehicleExample.createCriteria().andBatteryHealthEqualTo(vehicleQueryReq.getBatteryHealth());
        }
        if (!vehicleMapper.selectByExample(vehicleExample).get(0).getIsDeleted()) {
            return vehicleMapper.selectByExample(vehicleExample).get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Vehicle> selectVehicles(VehicleQueryReq vehicleQueryReq) {
        VehicleExample vehicleExample = new VehicleExample();
        VehicleExample.Criteria criteria = vehicleExample.createCriteria();
        if (vehicleQueryReq.getVid() != null) {
            criteria.andVidEqualTo(vehicleQueryReq.getVid());
        }
        if (vehicleQueryReq.getBattery() != null) {
            criteria.andBatteryEqualTo(vehicleQueryReq.getBattery());
        }
        if (vehicleQueryReq.getMileage() != null) {
            criteria.andMileageEqualTo(vehicleQueryReq.getMileage());
        }
        if (vehicleQueryReq.getBatteryHealth() != null) {
            criteria.andBatteryHealthEqualTo(vehicleQueryReq.getBatteryHealth());
        }
        criteria.andIsDeletedEqualTo(false);
        if (vehicleMapper.selectByExample(vehicleExample)!=null) {
            return vehicleMapper.selectByExample(vehicleExample);
        } else {
            return null;
        }
    }
}
