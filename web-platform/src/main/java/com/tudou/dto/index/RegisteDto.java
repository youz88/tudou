package com.tudou.dto.index;

import com.tudou.common.encrypt.Encrypt;
import com.tudou.common.json.JsonUtil;
import com.tudou.common.util.U;
import com.tudou.user.model.BaseUser;
import com.tudou.util.WebPlatformSessionUtil;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisteDto {

    @ApiParam("用户名")
    private String userName;

    @ApiParam("密码")
    private String password;

    @ApiParam("验证码")
    private String verity;

    public BaseUser userData(){
        U.assertLength(userName,5,20,"用户名必须5到20位");
        U.assertLength(password,6,12,"密码必须5到20位");
        U.assertException(!WebPlatformSessionUtil.checkImageCode(verity),"验证码错误，请重新输入");
        this.password = Encrypt.toMd5(password);
        return JsonUtil.convert(this,BaseUser.class);
    }
}
