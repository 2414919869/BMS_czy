package com.czy.bms.controller;

import com.czy.bms.request.UserInfoSaveReq;
import com.czy.bms.response.CommonResp;
import com.czy.bms.service.UserInfoService;
import com.czy.bms.service.VehicleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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


}
