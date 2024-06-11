package com.czy.bms.service.Impl;

import com.czy.bms.entity.UserInfo;
import com.czy.bms.entity.UserInfoExample;
import com.czy.bms.entity.Vehicle;
import com.czy.bms.entity.VehicleExample;
import com.czy.bms.mapper.UserInfoMapper;
import com.czy.bms.mapper.VehicleMapper;
import com.czy.bms.request.UserInfoQueryVehiclesReq;
import com.czy.bms.request.UserInfoSaveReq;
import com.czy.bms.service.UserInfoService;
import com.czy.bms.util.RandomIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private VehicleMapper vehicleMapper;

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

    @Override
    public boolean login(UserInfo loginUserInfo) {
        Integer username = loginUserInfo.getUserName();
        String password = loginUserInfo.getPassword();
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserNameEqualTo(username);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        if (userInfos!=null&&userInfos.size()>0){
            UserInfo userInfo=userInfos.get(0);
            if (password.equals(userInfo.getPassword())){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Vehicle> getMyVehicles(UserInfoQueryVehiclesReq userInfoQueryVehiclesReq) {
        VehicleExample vehicleExample = new VehicleExample();
        VehicleExample.Criteria criteria = vehicleExample.createCriteria();
        criteria.andIsDeletedEqualTo(false);
        criteria.andUidEqualTo(userInfoQueryVehiclesReq.getUid());
        if (userInfoQueryVehiclesReq.getVid()!=null){
            criteria.andVidEqualTo(userInfoQueryVehiclesReq.getVid());
        }
        if (userInfoQueryVehiclesReq.getBattery()!=null){
            criteria.andBatteryEqualTo(userInfoQueryVehiclesReq.getBattery());
        }
        if(userInfoQueryVehiclesReq.getMileage()!=null){
            criteria.andMileageEqualTo(userInfoQueryVehiclesReq.getMileage());
        }
        if (userInfoQueryVehiclesReq.getBatteryHealth()!=null){
            criteria.andBatteryHealthEqualTo(userInfoQueryVehiclesReq.getBatteryHealth());
        }
        List<Vehicle> vehicles = vehicleMapper.selectByExample(vehicleExample);
        if (vehicles!=null&&vehicles.size()>0){
            return vehicles;
        } else {
            return null;
        }
    }

}
