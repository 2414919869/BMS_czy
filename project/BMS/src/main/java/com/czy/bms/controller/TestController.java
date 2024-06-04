package com.czy.bms.controller;


import com.czy.bms.response.CommonResp;
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

}
