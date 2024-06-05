package com.czy.bms.controller;

import com.czy.bms.response.CommonResp;
import com.czy.bms.service.VehicleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Resource
    private VehicleService vehicleService;

    @GetMapping("/count")
    public CommonResp countVehicle() {
        return new CommonResp().success()
                .data(vehicleService.countVehicle());
    }
}
