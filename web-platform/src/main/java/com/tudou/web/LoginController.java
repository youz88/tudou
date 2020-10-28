package com.tudou.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.liuanxin.api.annotation.ApiGroup;
import com.github.liuanxin.api.annotation.ApiMethod;
import com.tudou.base.model.Permission;
import com.tudou.base.model.Role;
import com.tudou.base.model.User;
import com.tudou.base.service.UserService;
import com.tudou.common.Const;
import com.tudou.common.annotation.NotNeedLogin;
import com.tudou.common.json.JsonResult;
import com.tudou.dto.LoginDto;
import com.tudou.util.WebPlatformSessionUtil;
import com.tudou.vo.LoginVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ApiGroup("登录相关接口")
@RestController
@RequestMapping
public class LoginController {

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private UserService userService;

    @ApiMethod("登录")
    @PostMapping("/login")
    @NotNeedLogin
    public JsonResult<LoginVo> login(@RequestBody LoginDto loginDto){
        User user = userService.login(loginDto.userData());
        List<Role> roles = userService.listRoleByUser(user.getId());
        List<Permission> permissions = userService.listPermissionByRole(roles);
        WebPlatformSessionUtil.whenLogin(user,permissions);
        return JsonResult.success("登录成功", LoginVo.loginVoData(user,permissions));
    }

    @ApiMethod("注销")
    @PostMapping("/logout")
    @NotNeedLogin
    public JsonResult logout(){
        WebPlatformSessionUtil.logout();
        return JsonResult.success("注销成功");
    }
}
