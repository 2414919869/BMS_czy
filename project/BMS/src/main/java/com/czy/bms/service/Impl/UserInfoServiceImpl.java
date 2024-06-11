package com.czy.bms.service.Impl;

import com.czy.bms.entity.UserInfo;
import com.czy.bms.mapper.UserInfoMapper;
import com.czy.bms.request.UserInfoSaveReq;
import com.czy.bms.service.UserInfoService;
import com.czy.bms.util.RandomIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public long register(UserInfoSaveReq userInfoSaveReq) {
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(userInfoSaveReq.getUserName());
        userInfo.setPassword(userInfoSaveReq.getPassword());
        userInfo.setUid(new RandomIdUtil().getRandomId());
        userInfo.setStatus(userInfoSaveReq.getStatus());
        userInfo.setCreatedTime(new Date());
        userInfo.setUpdatedTime(new Date());
        userInfo.setIsDeleted(false);
        userInfoMapper.insert(userInfo);
        return userInfo.getUid();
    }
}
