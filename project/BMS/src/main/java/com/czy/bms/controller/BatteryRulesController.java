package com.czy.bms.controller;

import com.czy.bms.entity.BatteryRules;
import com.czy.bms.request.BatteryRulesQueryReq;
import com.czy.bms.request.BatteryRulesSaveReq;
import com.czy.bms.response.CommonResp;
import com.czy.bms.service.BatteryRulesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rules")
public class BatteryRulesController {
    @Resource
    private BatteryRulesService batteryRulesService;

    @GetMapping("/count")
    public CommonResp countRules(){
        return new CommonResp().success()
                .data(batteryRulesService.countRules());
    }

    @GetMapping("/get-all")
    public CommonResp getAllRules(){
        return new CommonResp().success()
                .data(batteryRulesService.getAllRules());
    }

    @GetMapping("/get/{rid}")
    public CommonResp getRule(@PathVariable("rid") int rid){
        return new CommonResp().success()
                .data(batteryRulesService.getRuleByRid(rid));
    }

    @DeleteMapping("/delete/{rid}")
    public CommonResp delete(@PathVariable("rid") int rid){
        if (batteryRulesService.deleteByRid(rid)){
            return new CommonResp().success();
        } else {
            return new CommonResp().error();
        }
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody BatteryRulesSaveReq batteryRulesSaveReq){
        batteryRulesService.insertRules(batteryRulesSaveReq);
        return new CommonResp().success();
    }

    @GetMapping("/select/{rid}")
    public CommonResp selectByRig(@PathVariable("rid") int rid){
        BatteryRules batteryRules = batteryRulesService.selectByRid(rid);
        if (batteryRules==null){
            return new CommonResp().error();
        } else {
            return new CommonResp().success()
                    .data(batteryRules);
        }
    }

    @PostMapping("/select/more")
    public CommonResp selectBatteryRules(@Valid @RequestBody BatteryRulesQueryReq batteryRulesQueryReq){
        List<BatteryRules> rules=batteryRulesService.selectRules(batteryRulesQueryReq);
        if (rules==null){
            return new CommonResp().error();
        } else {
            return new CommonResp().success()
                    .data(rules);
        }
    }

    @PostMapping("/update")
    public CommonResp updateByRid(@Valid @RequestBody BatteryRulesQueryReq batteryRulesQueryReq){
        batteryRulesService.updateByRid(batteryRulesQueryReq);
        return new CommonResp().success();
    }
}
