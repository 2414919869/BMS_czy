package com.czy.bms.service;

import com.czy.bms.entity.BatteryRules;
import com.czy.bms.request.BatteryRulesQueryReq;
import com.czy.bms.request.BatteryRulesSaveReq;

import java.util.List;

public interface BatteryRulesService {
    long countRules();
    List<BatteryRules> getAllRules();
    BatteryRules getRuleByRid(int rid);
    boolean deleteByRid(int rid);
    void insertRules(BatteryRulesSaveReq batteryRulesSaveReq);
    BatteryRules selectByRid(int rid);
    List<BatteryRules> selectRules(BatteryRulesQueryReq batteryRulesQueryReq);
    void updateByRid(BatteryRulesQueryReq batteryRulesQueryReq);
}
