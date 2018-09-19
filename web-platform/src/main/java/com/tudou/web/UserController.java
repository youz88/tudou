package com.tudou.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tudou.common.Const;
import com.tudou.common.Page;
import com.tudou.common.PageInfo;
import com.tudou.common.json.JsonResult;
import com.tudou.common.util.U;
import com.tudou.dto.UserDto;
import com.tudou.dto.UserListDto;
import com.tudou.user.model.User;
import com.tudou.user.service.UserService;
import com.tudou.util.WebPlatformSessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Api("用户接口相关接口")
@RestController
@RequestMapping("/user")
public class UserController {


    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private UserService userService;

    @ApiOperation("当前用户详情")
    @GetMapping("/info")
    public JsonResult info(){
        User user = userService.getByUserId(WebPlatformSessionUtil.getUserId());
        return JsonResult.success("当前用户详情",user);
    }

    @ApiOperation("查询用户信息")
    @GetMapping("/{userId}")
    public JsonResult info(@PathVariable("userId") Long userId){
        Assert.isTrue(U.isNotBlank(userId),"用户ID不能为空");
        User user = userService.getByUserId(userId);
        return JsonResult.success("查询用户信息",user);
    }

    @ApiOperation("用户详情")
    @GetMapping("/list")
    public JsonResult list(UserListDto userListDto, Page page){
        PageInfo<User> pageInfo = userService.listByParam(userListDto.userData(),page);
        return JsonResult.success("用户列表",pageInfo);
    }

    @ApiOperation("新增用户")
    @PostMapping
    public JsonResult save(UserDto userDto){
        userService.save(userDto.addUserData());
        return JsonResult.success("新增用户成功");
    }

    @ApiOperation("修改用户")
    @PutMapping
    public JsonResult update(UserDto userDto){
        userService.update(userDto.updateUserData());
        return JsonResult.success("修改用户成功");
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{userId}")
    public JsonResult delete(@PathVariable("userId") Long userId){
        Assert.isTrue(U.isNotBlank(userId),"用户ID不能为空");
        userService.delete(userId);
        return JsonResult.success("删除用户成功");
    }
}
