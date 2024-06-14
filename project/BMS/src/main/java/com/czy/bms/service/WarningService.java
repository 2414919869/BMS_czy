package com.czy.bms.service;

import com.czy.bms.request.VehicleSaveReq;
import com.czy.bms.request.WariningReq;
import com.czy.bms.response.WarningResp;

import java.util.List;

public interface WarningService {
    void insertMany(List<VehicleSaveReq> vehicleSaveReqs);
    List<WarningResp> getWarnings(List<WariningReq> wariningReqs);
}
