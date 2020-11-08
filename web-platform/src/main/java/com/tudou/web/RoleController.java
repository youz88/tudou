package com.tudou.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.liuanxin.api.annotation.ApiGroup;
import com.github.liuanxin.api.annotation.ApiMethod;
import com.github.liuanxin.api.annotation.ApiParam;
import com.tudou.base.model.Role;
import com.tudou.base.service.RoleService;
import com.tudou.common.Const;
import com.tudou.common.Page;
import com.tudou.common.PageInfo;
import com.tudou.common.json.JsonResult;
import com.tudou.common.util.A;
import com.tudou.common.util.U;
import com.tudou.dto.ListDto;
import com.tudou.dto.RoleDto;
import com.tudou.dto.RoleListDto;
import com.tudou.vo.RoleVo;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;


@ApiGroup("角色相关接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private RoleService roleService;

    @ApiMethod("角色列表")
    @GetMapping("/page")
    public JsonResult<PageInfo<RoleVo>> page(RoleListDto roleListDto, Page page){
        PageInfo<Role> pageInfo = roleService.page(roleListDto.roleData(),page);
        return JsonResult.success("角色列表",RoleVo.assemblyData(pageInfo));
    }

    @ApiMethod("角色信息")
    @GetMapping("/{id}")
    public JsonResult<RoleVo> info(@PathVariable("id") @ApiParam("角色ID") Long id){
        Assert.isTrue(U.isNotBlank(id),"角色ID不能为空");
        Role role = roleService.getById(id);
        return JsonResult.success("角色信息",RoleVo.assemblyData(role));
    }

    @ApiMethod("新增角色")
    @PostMapping
    public JsonResult save(@RequestBody RoleDto roleDto){
        roleService.save(roleDto.addRoleData());
        return JsonResult.success("新增角色成功");
    }

    @ApiMethod("修改角色")
    @PutMapping
    public JsonResult update(@RequestBody RoleDto roleDto){
        roleService.update(roleDto.updateRoleData());
        return JsonResult.success("修改角色成功");
    }

    @ApiMethod("删除角色")
    @DeleteMapping
    public JsonResult delete(@RequestBody ListDto listDto){
        Assert.isTrue(A.isNotEmpty(listDto.getIds()),"角色ID不能为空");
        roleService.delete(listDto.getIds());
        return JsonResult.success("删除角色成功");
    }
}
