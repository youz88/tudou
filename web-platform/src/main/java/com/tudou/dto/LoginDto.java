package com.tudou.dto;

import com.github.liuanxin.api.annotation.ApiParam;
import com.tudou.base.model.User;
import com.tudou.common.json.JsonUtil;
import lombok.Data;

@Data
public class LoginDto {

    @ApiParam("用户名")
    private String username;

    @ApiParam("密码")
    private String password;

    public User userData(){
        return JsonUtil.convert(this,User.class);
    }
}
