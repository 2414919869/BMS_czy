package com.czy.bms.controller;

import com.czy.bms.request.VehicleQueryReq;
import com.czy.bms.request.VehicleSaveReq;
import com.czy.bms.response.CommonResp;
import com.czy.bms.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private VehicleService vehicleService;

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
        if (vehicleService.delete(vid)) {
            return new CommonResp().success();
        } else {
            return new CommonResp().error();
        }
    }

    @PostMapping("/save")
    public CommonResp insertVehicle(@Valid @RequestBody VehicleSaveReq vehicleSaveReq) {
        vehicleService.insertVehicle(vehicleSaveReq);
        return new CommonResp().success();
    }

    @GetMapping("/query/{vid}")
    public CommonResp selectByVid(@PathVariable("vid") Short vid) {
        if (vehicleService.selectByVid(vid) != null) {
            return new CommonResp().success()
                    .data(vehicleService.selectByVid(vid));
        } else {
            return new CommonResp().error();
        }
    }

    @PostMapping("/query/more")
    public CommonResp selectVehicles(@Valid @RequestBody VehicleQueryReq vehicleQueryReq) {
        if (vehicleService.selectVehicles(vehicleQueryReq) != null) {
            return new CommonResp().success()
                    .data(vehicleService.selectVehicles(vehicleQueryReq));
        } else {
            return new CommonResp().error();
        }
    }

    @PostMapping("/update")
    public CommonResp updateByVid(@Valid @RequestBody VehicleQueryReq vehicleQueryReq) {
        vehicleService.updateByVid(vehicleQueryReq);
        return new CommonResp().success();
    }
}
