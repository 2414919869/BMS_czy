package com.czy.bms;

import com.czy.bms.request.BatteryRulesQueryReq;
import com.czy.bms.request.BatteryRulesSaveReq;
import com.czy.bms.service.BatteryRulesService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class BatteryRulesTest {

    @Resource
    private BatteryRulesService batteryRulesService;

    @Test
    public void countTest(){
        System.out.println(batteryRulesService.countRules());
    }

    @Test
    public void selectAll(){
        System.out.println(batteryRulesService.getAllRules());
    }

    @Test
    public void getRuleByRid(){
        System.out.println(batteryRulesService.getRuleByRid(2));
    }

    @Test
    public void deleteRuleByRid(){
        System.out.println(batteryRulesService.deleteByRid(1));
    }

    @Test
    public void insertRule(){
        BatteryRulesSaveReq batteryRulesSaveReq = new BatteryRulesSaveReq();
        batteryRulesSaveReq.setVid((short) 5);
        batteryRulesSaveReq.setRuleId("1");
        batteryRulesSaveReq.setName("电压差预警");
        batteryRulesSaveReq.setBattery("三元电池");
        batteryRulesService.insertRules(batteryRulesSaveReq);
    }

    @Test
    public void selectRules(){
        BatteryRulesQueryReq batteryRulesQueryReq = new BatteryRulesQueryReq();
        batteryRulesQueryReq.setName("电压差预警");
        System.out.println(batteryRulesService.selectRules(batteryRulesQueryReq));
    }

    @Test
    public void updateRulesTest(){
        BatteryRulesQueryReq batteryRulesQueryReq = new BatteryRulesQueryReq();
        batteryRulesQueryReq.setRid(1);
        batteryRulesQueryReq.setRuleId("2");
        batteryRulesQueryReq.setName("电流差预警");
        batteryRulesService.updateByRid(batteryRulesQueryReq);
    }
}
