package com.tudou.dto;

import com.tudou.common.json.JsonUtil;
import com.tudou.user.model.User;
import io.swagger.annotations.ApiParam;
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
