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
    public boolean deleteByVid(Short vid) {
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
    public void insertVehicle(VehicleSaveReq vehicleSaveReq) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleSaveReq, vehicle);
        vehicle.setId(new RandomIdUtil().getRandomId());
        vehicle.setCreatedTime(new Date());
        vehicle.setUpdatedTime(new Date());
        vehicle.setIsDeleted(false);
        vehicleMapper.insert(vehicle);
    }

    @Override
    public Vehicle selectByVid(Short vid) {
        VehicleExample vehicleExample = new VehicleExample();
        VehicleExample.Criteria criteria = vehicleExample.createCriteria();
        criteria.andVidEqualTo(vid);
        if (vehicleMapper.selectByExample(vehicleExample)==null||vehicleMapper.selectByExample(vehicleExample).size()==0)
        {
            return null;
        } else {
            if (!vehicleMapper.selectByExample(vehicleExample).get(0).getIsDeleted()) {
                return vehicleMapper.selectByExample(vehicleExample).get(0);
            } else {
                return null;
            }
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

    @Override
    public void updateByVid(VehicleQueryReq vehicleQueryReq) {
        VehicleExample vehicleExample = new VehicleExample();
        vehicleExample.createCriteria().andVidEqualTo(vehicleQueryReq.getVid());
        List<Vehicle> vehicles = vehicleMapper.selectByExample(vehicleExample);
        if (vehicles==null||vehicles.size()==0){
            Vehicle vehicle_insert = new Vehicle();
            BeanUtils.copyProperties(vehicleQueryReq, vehicle_insert);
            vehicle_insert.setVid(vehicleQueryReq.getVid());
            vehicle_insert.setId(new RandomIdUtil().getRandomId());
            vehicle_insert.setCreatedTime(new Date());
            vehicle_insert.setUpdatedTime(new Date());
            vehicle_insert.setIsDeleted(false);
            vehicleMapper.insert(vehicle_insert);
        } else {
            Vehicle vehicle = vehicleMapper.selectByExample(vehicleExample).get(0);
            vehicle.setIsDeleted(false);
            if (vehicleQueryReq.getMileage() != null) {
                vehicle.setMileage(vehicleQueryReq.getMileage());
            }
            if (vehicleQueryReq.getBatteryHealth() != null) {
                vehicle.setBatteryHealth(vehicleQueryReq.getBatteryHealth());
            }
            if (vehicleQueryReq.getBattery() != null) {
                vehicle.setBattery(vehicleQueryReq.getBattery());
            }
            vehicle.setUpdatedTime(new Date());
            vehicleMapper.updateByPrimaryKeySelective(vehicle);
        }
    }
}
