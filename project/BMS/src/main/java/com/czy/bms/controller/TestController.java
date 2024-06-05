package com.czy.bms.controller;


import com.czy.bms.response.CommonResp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test1")
    public String test1() {
        return "hello test1!";
    }

    @GetMapping("/resp")
    public CommonResp testResp(){
        return new CommonResp().success()
                .data(new Date());
    }

    @GetMapping("/json")
    public CommonResp testJson(){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name","caizeyu");
        jsonObject1.put("status","admin");
        jsonObject1.put("time","20240605");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name","Tom");
        jsonObject2.put("status","user");
        jsonObject2.put("time","20240604");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        return new CommonResp().success()
                .data(jsonArray);
    }


}
