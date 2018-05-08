package com.tudou.dto;

import com.tudou.common.json.JsonUtil;
import com.tudou.user.model.BaseUser;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    @ApiParam("用户名")
    private String userName;

    @ApiParam("密码")
    private String password;

    @ApiParam("验证码")
    private String verity;

    public BaseUser userData(){
        return JsonUtil.convert(this,BaseUser.class);
    }
}
