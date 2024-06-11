package com.czy.bms.service;

import com.czy.bms.entity.UserInfo;
import com.czy.bms.entity.Vehicle;
import com.czy.bms.request.UserInfoQueryVehiclesReq;
import com.czy.bms.request.UserInfoSaveReq;

import java.util.List;

public interface UserInfoService {
    long register(UserInfoSaveReq userInfoSaveReq);
    boolean login(UserInfo loginUserInfo);
    List<Vehicle> getMyVehicles(UserInfoQueryVehiclesReq userInfoQueryVehiclesReq);
}
