package com.czy.bms.response;

import lombok.Data;

@Data
public class CommonResp {
    private int code;
    private String message;
    private Object data;

    public static CommonResp success(){
        CommonResp commonResp = new CommonResp();
        commonResp.setCode(200);
        commonResp.setMessage("ok");
        return commonResp;
    }

    public static CommonResp error(){
        CommonResp commonResp = new CommonResp();
        commonResp.setCode(500);
        commonResp.setMessage("error");
        return commonResp;
    }

    public CommonResp data(Object data){
        this.data = data;
        return this;
    }
}
