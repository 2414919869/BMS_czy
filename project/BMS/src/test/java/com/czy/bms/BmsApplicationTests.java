package com.czy.bms;

import com.czy.bms.controller.VehicleController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BmsApplicationTests {

    @Resource
    private VehicleController vehicleController;



    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        System.out.println(vehicleController.countVehicle());
        assert vehicleController.countVehicle().getCode() == 200 ;
    }



}
