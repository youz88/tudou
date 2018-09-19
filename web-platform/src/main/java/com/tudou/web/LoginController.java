package com.tudou.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tudou.common.Const;
import com.tudou.common.annotation.NotNeedLogin;
import com.tudou.common.json.JsonResult;
import com.tudou.dto.LoginDto;
import com.tudou.user.model.Permission;
import com.tudou.user.model.Role;
import com.tudou.user.model.User;
import com.tudou.user.service.UserService;
import com.tudou.util.WebPlatformSessionUtil;
import com.tudou.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("登录接口相关接口")
@RestController
@RequestMapping("")
public class LoginController {

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    @NotNeedLogin
    public JsonResult login(LoginDto loginDto){
        User user = userService.login(loginDto.userData());
        List<Role> roles = userService.listRoleByUser(user.getId());
        List<Permission> permissions = userService.listPermissionByRole(roles);
        WebPlatformSessionUtil.whenLogin(user,permissions);
        return JsonResult.success("登录成功", LoginVo.loginVoData(user,permissions));
    }

    @ApiOperation("注销")
    @PostMapping("/logout")
    @NotNeedLogin
    public JsonResult logout(){
        WebPlatformSessionUtil.signOut();
        return JsonResult.success("注销成功");
    }
}
