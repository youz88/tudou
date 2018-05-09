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
public class LoginDto {

    @ApiParam("用户名")
    private String userName;

    @ApiParam("密码")
    private String password;

    @ApiParam("验证码")
    private String verity;

    public BaseUser userData(){
        U.assertNil(userName,"用户名不能为空");
        U.assertNil(password,"密码不能为空");
//        U.assertNil(verity,"验证码不能为空");
        if(U.isNotBlank(verity)){
            U.assertException(!WebPlatformSessionUtil.checkImageCode(verity),"验证码错误，请重新输入");
        }
        this.password = Encrypt.toMd5(password);
        return JsonUtil.convert(this,BaseUser.class);
    }
}
