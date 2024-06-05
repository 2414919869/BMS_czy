package com.czy.bms.controller;

import com.czy.bms.response.CommonResp;
import com.czy.bms.service.VehicleService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAll")
    public CommonResp getAllVehicle() {
        return new CommonResp().success()
                .data(vehicleService.getAllVehicles());
    }

    @GetMapping("/get-vid/{vid}")
    public CommonResp getVehicleByVid(@PathVariable("vid") Short vid) {
        return new CommonResp().success()
                .data(vehicleService.getVehiclesByVid(vid));
    }

    @DeleteMapping("/delete/{vid}")
    public CommonResp deleteByVid(@PathVariable("vid") Short vid) {
        if (vehicleService.deleteByVid(vid)) {
            return new CommonResp().success();
        } else {
            return new CommonResp().error();
        }
    }
}
