package com.tudou.web;

import com.tudou.dto.LoginDto;
import com.tudou.user.model.BaseUser;
import com.tudou.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登录接口")
@RestController
public class LoginController {

    @ApiOperation("登录")
    @PostMapping("/login")
    public UserVo login(LoginDto loginDto){
        return UserVo.assemblyData(new BaseUser());
    }
}
