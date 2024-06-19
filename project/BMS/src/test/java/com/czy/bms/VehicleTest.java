package com.czy.bms;

import com.czy.bms.request.VehicleQueryReq;
import com.czy.bms.request.VehicleSaveReq;
import com.czy.bms.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class VehicleTest {
    @Resource
    private VehicleService vehicleService;

    @Test
    public void countVehicleTest(){
        System.out.println(vehicleService.countVehicle());
    }

    @Test
    public void getAllVehicleTest(){
        System.out.println(vehicleService.getAllVehicles());
    }

    @Test
    public void getVehicleByIdTest(){
        System.out.println(vehicleService.getVehiclesByVid((short) 1));
        System.out.println(vehicleService.getVehiclesByVid((short) 2));
        System.out.println(vehicleService.getVehiclesByVid((short) 3));
        System.out.println(vehicleService.getVehiclesByVid((short) 4));
    }

    @Test
    public void deleteVehicleByIdTest(){
        System.out.println(vehicleService.deleteByVid((short) 5));
    }

    @Test
    public void insertVehicleTest(){
        VehicleSaveReq vehicleSaveReq = new VehicleSaveReq();
        vehicleSaveReq.setVid((short) 6);
        vehicleSaveReq.setBattery("铁锂电池");
        vehicleSaveReq.setMileage(2000);
        vehicleSaveReq.setBatteryHealth((byte) 30);
        vehicleService.insertVehicle(vehicleSaveReq);
    }

    @Test
    public void selectVehiclesTest(){
        VehicleQueryReq vehicleQueryReq = new VehicleQueryReq();
        vehicleQueryReq.setBattery("铁锂电池");
        System.out.println(vehicleService.selectVehicles(vehicleQueryReq));
    }

    @Test
    public void updateVehicleTest(){
        VehicleQueryReq vehicleQueryReq = new VehicleQueryReq();
        vehicleQueryReq.setVid((short) 5);
        vehicleQueryReq.setBattery("铁锂电池");
        vehicleService.updateByVid(vehicleQueryReq);
    }

    @Test
    public void deleteVehicleTest(){
        vehicleService.delete((short) 6);
    }
}
