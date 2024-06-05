package com.czy.bms.mapper;

import com.czy.bms.entity.Vehicle;
import com.czy.bms.entity.VehicleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VehicleMapper {
    long countByExample(VehicleExample example);

    int deleteByExample(VehicleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    List<Vehicle> selectByExample(VehicleExample example);

    Vehicle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Vehicle record, @Param("example") VehicleExample example);

    int updateByExample(@Param("record") Vehicle record, @Param("example") VehicleExample example);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);
}