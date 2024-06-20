package com.czy.bms.service.Impl;

import com.czy.bms.entity.BatteryRules;
import com.czy.bms.entity.BatteryRulesExample;
import com.czy.bms.mapper.BatteryRulesMapper;
import com.czy.bms.request.BatteryRulesQueryReq;
import com.czy.bms.request.BatteryRulesSaveReq;
import com.czy.bms.service.BatteryRulesService;
import com.czy.bms.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BatteryRulesServiceImpl implements BatteryRulesService {
    @Resource
    private BatteryRulesMapper batteryRulesMapper;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public long countRules() {
        return batteryRulesMapper.countByExample(null);
    }

    @Override
    public List<BatteryRules> getAllRules() {
//        BatteryRulesExample example = new BatteryRulesExample();
//        example.setOrderByClause("rid desc");
//        return batteryRulesMapper.selectByExample(example);

        String allRules=redisUtil.get("allRules");
        if(allRules==null){
            BatteryRulesExample example=new BatteryRulesExample();
            example.createCriteria().andIsDeletedEqualTo(false);
            example.setOrderByClause("id desc");
            redisUtil.set("allRules",String.valueOf(batteryRulesMapper.selectByExample(example)));
        }
        return batteryRulesMapper.selectByExample(null);
    }


    @Override
    public BatteryRules getRuleByRid(int rid) {
        BatteryRulesExample batteryRulesExample = new BatteryRulesExample();
        batteryRulesExample.createCriteria().andRidEqualTo(rid);
        List<BatteryRules> rules = batteryRulesMapper.selectByExample(batteryRulesExample);
        if (rules.size() > 0) {
            return rules.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteByRid(int rid) {
        BatteryRules batteryRules = getRuleByRid(rid);
        if (batteryRules != null && batteryRules.getIsDeleted() == false) {
            batteryRules.setIsDeleted(true);
            batteryRules.setUpdatedTime(new Date());
            batteryRulesMapper.updateByPrimaryKey(batteryRules);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void insertRules(BatteryRulesSaveReq batteryRulesSaveReq) {
        BatteryRules batteryRules = new BatteryRules();
        BeanUtils.copyProperties(batteryRulesSaveReq, batteryRules);
        batteryRules.setCreatedTime(new Date());
        batteryRules.setUpdatedTime(new Date());
        batteryRules.setIsDeleted(false);
        batteryRulesMapper.insert(batteryRules);
    }

    @Override
    public BatteryRules selectByRid(int rid) {
        BatteryRulesExample batteryRulesExample=new BatteryRulesExample();
        BatteryRulesExample.Criteria criteria = batteryRulesExample.createCriteria();
        criteria.andRidEqualTo(rid);
        List<BatteryRules> rules = batteryRulesMapper.selectByExample(batteryRulesExample);
        if (rules.size() == 0||rules==null) {
            return null;
        } else {
            if (rules.get(0).getIsDeleted() == false) {
                return rules.get(0);
            } else{
                return null;
            }
        }
    }

    @Override
    public List<BatteryRules> selectRules(BatteryRulesQueryReq batteryRulesQueryReq) {
//        BatteryRulesExample batteryRulesExample=new BatteryRulesExample();
//        BatteryRulesExample.Criteria criteria = batteryRulesExample.createCriteria();
//        if (batteryRulesQueryReq != null) {
//            if (batteryRulesQueryReq.getRid()!=null){
//                criteria.andRidEqualTo(batteryRulesQueryReq.getRid());
//            }
//            if (batteryRulesQueryReq.getVid()!=null){
//                criteria.andVidEqualTo(batteryRulesQueryReq.getVid());
//            }
//            if (batteryRulesQueryReq.getRuleId()!=null){
//                criteria.andRuleIdEqualTo(batteryRulesQueryReq.getRuleId());
//            }
//            if (batteryRulesQueryReq.getName()!=null){
//                criteria.andNameEqualTo(batteryRulesQueryReq.getName());
//            }
//            if (batteryRulesQueryReq.getBattery()!=null){
//                criteria.andBatteryEqualTo(batteryRulesQueryReq.getBattery());
//            }
//            criteria.andIsDeletedEqualTo(false);
//            List<BatteryRules> rules = batteryRulesMapper.selectByExample(batteryRulesExample);
//            if (rules.size() > 0) {
//                return rules;
//            } else {
//                return null;
//            }
//        } else {
//            return getAllRules();
//        }
        return null;
    }

    @Override
    public void updateByRid(BatteryRulesQueryReq batteryRulesQueryReq) {
        BatteryRulesExample batteryRulesExample=new BatteryRulesExample();
        BatteryRulesExample.Criteria criteria = batteryRulesExample.createCriteria();
        criteria.andRidEqualTo(batteryRulesQueryReq.getRid());
        List<BatteryRules> rules = batteryRulesMapper.selectByExample(batteryRulesExample);
        if (rules==null||rules.size()==0) {
            BatteryRules batteryRules = new BatteryRules();
            BeanUtils.copyProperties(batteryRulesQueryReq, batteryRules);
            batteryRules.setCreatedTime(new Date());
            batteryRules.setUpdatedTime(new Date());
            batteryRules.setIsDeleted(false);
            batteryRulesMapper.insert(batteryRules);
        } else {
            BatteryRules batteryRules = rules.get(0);
            batteryRules.setIsDeleted(false);
            if (batteryRulesQueryReq.getRid()!=null){
                batteryRules.setRid(batteryRulesQueryReq.getRid());
            }
            if (batteryRulesQueryReq.getVid()!=null){
                batteryRules.setVid(batteryRulesQueryReq.getVid());
            }
            if (batteryRulesQueryReq.getRuleId()!=null){
                batteryRules.setRuleId(batteryRulesQueryReq.getRuleId());
            }
            if (batteryRulesQueryReq.getName()!=null){
                batteryRules.setName(batteryRulesQueryReq.getName());
            }
            if (batteryRulesQueryReq.getBattery()!=null){
                batteryRules.setBattery(batteryRulesQueryReq.getBattery());
            }
            if (batteryRulesQueryReq.getLevel()!=null){
                batteryRules.setLevel(batteryRulesQueryReq.getLevel());
            }
            batteryRules.setUpdatedTime(new Date());
            batteryRulesMapper.updateByPrimaryKey(batteryRules);
        }
    }
}
