package com.czy.bms.controller;

import com.czy.bms.request.VehicleSaveReq;
import com.czy.bms.request.WariningReq;
import com.czy.bms.response.CommonResp;
import com.czy.bms.service.WarningService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/warn")
public class WarnController {

    @Resource
    private WarningService warningService;

    @PostMapping("/insert")
    public void insert(@Valid @RequestBody List<VehicleSaveReq> vehicleSaveReqs) {
        warningService.insertMany(vehicleSaveReqs);
    }

    @PostMapping("getLevel")
    public CommonResp getLevels(@Valid @RequestBody List<WariningReq> wariningReqs){
        return new CommonResp().success()
                .data(warningService.getWarnings(wariningReqs));
    }


}
