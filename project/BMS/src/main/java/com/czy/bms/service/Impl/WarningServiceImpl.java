package com.czy.bms.service.Impl;


import com.czy.bms.entity.BatteryRules;
import com.czy.bms.entity.Vehicle;
import com.czy.bms.mapper.BatteryRulesMapper;
import com.czy.bms.mapper.VehicleMapper;
import com.czy.bms.request.BatteryRulesQueryReq;
import com.czy.bms.request.VehicleSaveReq;
import com.czy.bms.request.WariningReq;
import com.czy.bms.response.WarningResp;
import com.czy.bms.service.BatteryRulesService;
import com.czy.bms.service.WarningService;
import com.czy.bms.util.RandomIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WarningServiceImpl implements WarningService {

    @Resource
    private VehicleMapper vehicleMapper;

    @Resource
    private BatteryRulesMapper batteryRulesMapper;

    @Resource
    private BatteryRulesService batteryRulesService;

    @Override
    public void insertMany(List<VehicleSaveReq> vehicleSaveReqs) {
        for (VehicleSaveReq vehicleSaveReq : vehicleSaveReqs) {
            Vehicle vehicle = new Vehicle();
            BeanUtils.copyProperties(vehicleSaveReq, vehicle);
            vehicle.setId(new RandomIdUtil().getRandomId());
            vehicle.setCreatedTime(new Date());
            vehicle.setUpdatedTime(new Date());
            vehicle.setIsDeleted(false);
            vehicleMapper.insert(vehicle);
        }
    }

    public float getValue(String input, String variable) {
        int startIndex = input.indexOf(variable) + variable.length() + 2;
        int endIndex = input.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = input.indexOf("}", startIndex);
        }
        String valueString = input.substring(startIndex, endIndex);
        return Float.parseFloat(valueString);
    }

    @Override
    public List<WarningResp> getWarnings(List<WariningReq> wariningReqs) {
        List<WarningResp> warningResps = new ArrayList<>();
        for (WariningReq wariningReq : wariningReqs) {
            BatteryRulesQueryReq batteryRulesQueryReq = new BatteryRulesQueryReq();
            batteryRulesQueryReq.setVid(wariningReq.getCarId());
            batteryRulesQueryReq.setRuleId(wariningReq.getWarningId());
            if (wariningReq.getWarningId() == "1") {
                batteryRulesQueryReq.setName("电压差报警");
            }
            if (wariningReq.getWarningId() == "2") {
                batteryRulesQueryReq.setName("电流差报警");
            }
            BatteryRules batteryRule = batteryRulesService.selectRules(batteryRulesQueryReq).get(0);
            String ruleId = batteryRule.getRuleId();
            String battery = batteryRule.getBattery();
            if (ruleId != null) {
                String level = "";
                if (ruleId.equals("1")) {
                    float Mx = getValue(wariningReq.getSignal(), "Mx");
                    float Mi = getValue(wariningReq.getSignal(), "Mi");
                    float diffM = Mx - Mi;
                    if (battery.equals("三元电池")) {
                        if (diffM >= 5) level = "0";
                        else if (diffM < 5 && diffM >= 3) level = "1";
                        else if (diffM < 3 && diffM >= 1) level = "2";
                        else if (diffM < 1 && diffM >= 0.6) level = "3";
                        else if (diffM < 0.6 && diffM >= 0.2) level = "4";
                        else level = "-1";
                    }
                    if (battery.equals("铁锂电池")) {
                        if (diffM >= 2) level = "0";
                        else if (diffM < 2 && diffM >= 1) level = "1";
                        else if (diffM < 1 && diffM >= 0.7) level = "2";
                        else if (diffM < 0.7 && diffM >= 0.4) level = "3";
                        else if (diffM < 0.4 && diffM >= 0.2) level = "4";
                        else level = "-1";
                    }
                }
                if (ruleId.equals("2")) {
                    float Ix = getValue(wariningReq.getSignal(), "Ix");
                    float Ii = getValue(wariningReq.getSignal(), "Ii");
                    float diffI = Ix - Ii;
                    if (battery.equals("三元电池")) {
                        if (diffI >= 3) level = "0";
                        else if (diffI < 3 && diffI >= 1) level = "1";
                        else if (diffI < 1 && diffI >= 0.2) level = "2";
                        else level = "-1";
                    }
                    if (battery.equals("铁锂电池")) {
                        if (diffI >= 1) level = "0";
                        else if (diffI < 1 && diffI >= 0.5) level = "1";
                        else if (diffI < 0.5 && diffI >= 0.2) level = "2";
                        else level = "-1";
                    }
                }
                batteryRule.setLevel(level);
                BatteryRulesQueryReq req = new BatteryRulesQueryReq();
                BeanUtils.copyProperties(batteryRule, req);
                batteryRulesService.updateByRid(req);
                WarningResp warningResp = new WarningResp();
                warningResp.setVid(wariningReq.getCarId());
                warningResp.setBattery(battery);
                warningResp.setWarningId(ruleId);
                if (ruleId.equals("1")) warningResp.setWarningName("电压差报警");
                if (ruleId.equals("2")) warningResp.setWarningName("电流差报警");
                if (!level.equals("-1")) {
                    warningResp.setWarningLevel(level);
                    warningResps.add(warningResp);
                }
            } else {
                String levelM = "";
                String levelI = "";
                float Mx = getValue(wariningReq.getSignal(), "Mx");
                float Mi = getValue(wariningReq.getSignal(), "Mi");
                float diffM = Mx - Mi;
                if (battery.equals("三元电池")) {
                    if (diffM >= 5) levelM = "0";
                    else if (diffM < 5 && diffM >= 3) levelM = "1";
                    else if (diffM < 3 && diffM >= 1) levelM = "2";
                    else if (diffM < 1 && diffM >= 0.6) levelM = "3";
                    else if (diffM < 0.6 && diffM >= 0.2) levelM = "4";
                    else levelM = "-1";
                }
                if (battery.equals("铁锂电池")) {
                    if (diffM >= 2) levelM = "0";
                    else if (diffM < 2 && diffM >= 1) levelM = "1";
                    else if (diffM < 1 && diffM >= 0.7) levelM = "2";
                    else if (diffM < 0.7 && diffM >= 0.4) levelM = "3";
                    else if (diffM < 0.4 && diffM >= 0.2) levelM = "4";
                    else levelM = "-1";
                }


                batteryRule.setLevel(levelM);
                BatteryRulesQueryReq req = new BatteryRulesQueryReq();
                BeanUtils.copyProperties(batteryRule, req);
                batteryRulesService.updateByRid(req);
                WarningResp warningResp = new WarningResp();
                warningResp.setVid(wariningReq.getCarId());
                warningResp.setBattery(battery);
                warningResp.setWarningId("1");
                warningResp.setWarningName("电压差报警");
                if (!levelM.equals("-1")) {
                    warningResp.setWarningLevel(levelM);
                    warningResps.add(warningResp);
                }


                float Ix = getValue(wariningReq.getSignal(), "Ix");
                float Ii = getValue(wariningReq.getSignal(), "Ii");
                float diffI = Ix - Ii;
                if (battery.equals("三元电池")) {
                    if (diffI >= 3) levelI = "0";
                    else if (diffI < 3 && diffI >= 1) levelI = "1";
                    else if (diffI < 1 && diffI >= 0.2) levelI = "2";
                    else levelI = "-1";
                }
                if (battery.equals("铁锂电池")) {
                    if (diffI >= 1) levelI = "0";
                    else if (diffI < 1 && diffI >= 0.5) levelI = "1";
                    else if (diffI < 0.5 && diffI >= 0.2) levelI = "2";
                    else levelI = "-1";
                }
                BatteryRules newBatteryRule = new BatteryRules();
                BeanUtils.copyProperties(batteryRule, newBatteryRule);
                newBatteryRule.setLevel(levelI);
                BatteryRulesQueryReq req1 = new BatteryRulesQueryReq();
                BeanUtils.copyProperties(newBatteryRule, req1);
                batteryRulesService.updateByRid(req1);
                WarningResp warningResp1 = new WarningResp();
                warningResp1.setVid(wariningReq.getCarId());
                warningResp1.setBattery(battery);
                warningResp1.setWarningId("2");
                warningResp1.setWarningName("电流差报警");
                if (!levelI.equals("-1")) {
                    warningResp1.setWarningLevel(levelI);
                    warningResps.add(warningResp1);
                }
            }

        }
        return warningResps;
    }
}
