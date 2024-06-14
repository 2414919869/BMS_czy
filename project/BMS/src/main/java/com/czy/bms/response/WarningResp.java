package com.czy.bms.response;

import lombok.Data;

@Data
public class WarningResp {
    private Short vid;
    private String warningId;
    private String battery;
    private String warningName;
    private String warningLevel;
}
