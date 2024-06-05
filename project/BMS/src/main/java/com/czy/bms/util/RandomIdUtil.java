package com.czy.bms.util;

import java.util.Date;

public class RandomIdUtil {
    public Long getRandomId(){
        Date date = new Date();
        int i=(int)(Math.random()*900)+100;
        String str_i=String.valueOf(i);
        return Long.valueOf(date.getTime()+str_i);
    }
}
