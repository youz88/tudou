package com.tudou.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tudou.common.Const;
import com.tudou.common.json.JsonResult;
import com.tudou.common.json.JsonUtil;
import com.tudou.dto.index.LoginDto;
import com.tudou.dto.index.RegisteDto;
import com.tudou.user.model.BasePermission;
import com.tudou.user.model.BaseUser;
import com.tudou.user.service.PermissionService;
import com.tudou.user.service.UserService;
import com.tudou.util.WebPlatformSessionUtil;
import com.tudou.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "主页接口")
@RestController
public class IndexController {

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    UserService userService;
    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    PermissionService permissionService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public JsonResult<UserVo> login(LoginDto loginDto){
        BaseUser baseUser = userService.login(loginDto.userData());
        List<BasePermission> basePermissionList = permissionService.getPermissionByUser(baseUser.getId());
        WebPlatformSessionUtil.whenLogin(baseUser,basePermissionList);
        return JsonResult.success("用户信息",UserVo.assemblyData(baseUser,basePermissionList));
    }

    @ApiOperation("注册")
    @PostMapping("/registe")
    public JsonResult registe(RegisteDto registeDto){
        userService.registe(registeDto.userData());
        return JsonResult.success("注册成功");
    }
}
