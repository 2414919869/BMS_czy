package com.czy.bms;

import com.czy.bms.entity.UserInfo;
import com.czy.bms.request.UserInfoSaveReq;
import com.czy.bms.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserInfoTest {
    @Resource
    private UserInfoService userInfoService;

    @Test
    public void registerTest(){
        UserInfoSaveReq userInfoSaveReq = new UserInfoSaveReq();
        userInfoSaveReq.setUserName(123123123);
        userInfoSaveReq.setPassword("123456");
        userInfoSaveReq.setStatus("普通用户");
        System.out.println(userInfoService.register(userInfoSaveReq));
    }

    @Test
    public void loginTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(123123123);
        userInfo.setPassword("123");
        System.out.println(userInfoService.login(userInfo));
    }


}
