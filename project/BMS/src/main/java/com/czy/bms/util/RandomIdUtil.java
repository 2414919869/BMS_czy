package com.czy.bms.util;

import java.util.Date;

public class RandomIdUtil {
    public String getRandomId(){
        Date date = new Date();
        int i=(int)(Math.random()*900)+100;
        String str_i=String.valueOf(i);
        return date.getTime()+str_i;
    }
}
