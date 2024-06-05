package com.czy.bms.mapper;

import com.czy.bms.entity.BatteryRules;
import com.czy.bms.entity.BatteryRulesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BatteryRulesMapper {
    long countByExample(BatteryRulesExample example);

    int deleteByExample(BatteryRulesExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(BatteryRules record);

    int insertSelective(BatteryRules record);

    List<BatteryRules> selectByExample(BatteryRulesExample example);

    BatteryRules selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") BatteryRules record, @Param("example") BatteryRulesExample example);

    int updateByExample(@Param("record") BatteryRules record, @Param("example") BatteryRulesExample example);

    int updateByPrimaryKeySelective(BatteryRules record);

    int updateByPrimaryKey(BatteryRules record);
}