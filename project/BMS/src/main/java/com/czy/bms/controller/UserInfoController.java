package com.czy.bms.controller;

import com.czy.bms.entity.UserInfo;
import com.czy.bms.entity.Vehicle;
import com.czy.bms.request.UserInfoQueryVehiclesReq;
import com.czy.bms.request.UserInfoSaveReq;
import com.czy.bms.request.VehicleQueryReq;
import com.czy.bms.response.CommonResp;
import com.czy.bms.service.UserInfoService;
import com.czy.bms.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private VehicleService vehicleService;

    @PostMapping("/register")
    public CommonResp register(@Valid @RequestBody UserInfoSaveReq userInfoSaveReq) {
        return new CommonResp().success()
                .data(userInfoService.register(userInfoSaveReq));
    }

    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserInfo userInfo, HttpSession session) {
        boolean flag = userInfoService.login(userInfo);
        if (flag) {
            session.setAttribute("userInfo", userInfo);
            return new CommonResp().success();
        } else {
            return CommonResp.error();
        }
    }

    @PostMapping("/get")
    public CommonResp getMyVehicles(@Valid @RequestBody UserInfoQueryVehiclesReq userInfoQueryVehiclesReq){
        List<Vehicle> list=userInfoService.getMyVehicles(userInfoQueryVehiclesReq);
        if (list.size()>0){
            return new CommonResp().success()
                    .data(list);
        } else {
            return CommonResp.error();
        }
    }

    @PostMapping("/updata")
    public CommonResp updateByVid(@Valid @RequestBody VehicleQueryReq vehicleQueryReq) {
        vehicleService.updateByVid(vehicleQueryReq);
        return new CommonResp().success();
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
