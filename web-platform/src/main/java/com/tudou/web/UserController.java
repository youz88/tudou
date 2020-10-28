package com.tudou.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.liuanxin.api.annotation.ApiGroup;
import com.github.liuanxin.api.annotation.ApiMethod;
import com.github.liuanxin.api.annotation.ApiParam;
import com.tudou.base.model.Role;
import com.tudou.base.model.User;
import com.tudou.base.model.UserRole;
import com.tudou.base.service.RoleService;
import com.tudou.base.service.UserService;
import com.tudou.common.Const;
import com.tudou.common.Page;
import com.tudou.common.PageInfo;
import com.tudou.common.json.JsonResult;
import com.tudou.common.util.A;
import com.tudou.common.util.U;
import com.tudou.dto.ListDto;
import com.tudou.dto.RegisteDto;
import com.tudou.dto.UserDto;
import com.tudou.dto.UserListDto;
import com.tudou.util.WebPlatformSessionUtil;
import com.tudou.vo.UserRoleVo;
import com.tudou.vo.UserVo;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiGroup("用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private UserService userService;
    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private RoleService roleService;

    @ApiMethod("注册")
    @PostMapping("/registe")
    public JsonResult<UserVo> registe(@RequestBody RegisteDto registeDto){
        User user = userService.registe(registeDto.userData());
        return JsonResult.success("注册成功", UserVo.assemblyData(user));
    }

    @ApiMethod("当前用户详情")
    @GetMapping("/info")
    public JsonResult<UserVo> info(){
        User user = userService.getById(WebPlatformSessionUtil.getUserId());
        return JsonResult.success("当前用户详情", UserVo.assemblyData(user));
    }

    @ApiMethod("查询用户信息")
    @GetMapping("/{id}")
    public JsonResult<UserVo> info(@PathVariable("id") @ApiParam("用户ID") Long id){
        Assert.isTrue(U.isNotBlank(id),"用户ID不能为空");
        User user = userService.getById(id);
        return JsonResult.success("查询用户信息", UserVo.assemblyData(user));
    }

    @ApiMethod("用户详情")
    @GetMapping("/list")
    public JsonResult<PageInfo<UserVo>> list(UserListDto userListDto, Page page){
        PageInfo<User> pageInfo = userService.listByParam(userListDto.userData(),page);
        return JsonResult.success("用户详情",UserVo.assemblyData(pageInfo));
    }

    @ApiMethod("新增用户")
    @PostMapping
    public JsonResult save(@RequestBody UserDto userDto){
        userService.save(userDto.addUserData());
        return JsonResult.success("新增用户成功");
    }

    @ApiMethod("修改用户")
    @PutMapping
    public JsonResult update(@RequestBody UserDto userDto){
        userService.update(userDto.updateUserData());
        return JsonResult.success("修改用户成功");
    }

    @ApiMethod("删除用户")
    @DeleteMapping
    public JsonResult delete(@RequestBody ListDto listDto){
        Assert.isTrue(A.isNotEmpty(listDto.getIds()),"用户ID不能为空");
        userService.delete(listDto.getIds());
        return JsonResult.success("删除用户成功");
    }

    @ApiMethod("查看用户角色")
    @GetMapping("/role/{userId}")
    public JsonResult<List<UserRoleVo>> role(@PathVariable @ApiParam("用户ID") Long userId){
        List<UserRole> userRoleList = userService.userRole(userId);
        List<Role> roleList = roleService.list();
        return JsonResult.success("用户角色", UserRoleVo.assemblyData(userRoleList,roleList));
    }

    @ApiMethod("编辑用户角色")
    @PutMapping("/role/{userId}")
    public JsonResult updateRole(@PathVariable Long userId, @RequestBody ListDto listDto){
        userService.updateUserRole(userId,listDto.getIds());
        return JsonResult.success("编辑用户角色成功");
    }
}
