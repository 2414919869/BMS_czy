package com.czy.bms.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserInfoSaveReq {
    @NotNull(message = "【用户名】不能为空！")
    private Integer userName;
    @NotBlank(message = "【密码】不能为空！")
    private String password;
    @NotNull(message = "【用户身份】不能为空！")
    private String status;
}
