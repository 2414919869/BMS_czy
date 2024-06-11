package com.czy.bms.service;

import com.czy.bms.request.UserInfoSaveReq;

public interface UserInfoService {
    long register(UserInfoSaveReq userInfoSaveReq);
}
