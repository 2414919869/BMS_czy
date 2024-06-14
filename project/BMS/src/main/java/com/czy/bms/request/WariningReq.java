package com.czy.bms.request;

import lombok.Data;

@Data
public class WariningReq {
    private short carId;
    private String warningId;
    private String signal;
}
