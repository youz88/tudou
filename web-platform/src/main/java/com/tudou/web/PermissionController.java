package com.tudou.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.liuanxin.api.annotation.ApiGroup;
import com.github.liuanxin.api.annotation.ApiMethod;
import com.github.liuanxin.api.annotation.ApiParam;
import com.tudou.base.model.Permission;
import com.tudou.base.service.PermissionService;
import com.tudou.common.Const;
import com.tudou.common.Page;
import com.tudou.common.PageInfo;
import com.tudou.common.json.JsonResult;
import com.tudou.common.util.A;
import com.tudou.common.util.U;
import com.tudou.dto.ListDto;
import com.tudou.dto.PermissionDto;
import com.tudou.dto.PermissionListDto;
import com.tudou.vo.PermissionTreeVo;
import com.tudou.vo.PermissionVo;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiGroup("权限相关接口")
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Reference(version = Const.DUBBO_VERSION, lazy = true, check = false, timeout = Const.DUBBO_TIMEOUT)
    private PermissionService permissionService;

    @ApiMethod("权限列表")
    @GetMapping("/list")
    public JsonResult<PageInfo<PermissionVo>> list(PermissionListDto permissionListDto, Page page){
        PageInfo<Permission> pageInfo = permissionService.listByParam(permissionListDto.permissionData(),page);
        return JsonResult.success("权限列表", PermissionVo.assemblyData(pageInfo));
    }

    @ApiMethod("通过权限类型查询")
    @GetMapping("/type")
    public JsonResult<List<PermissionVo>> list(@ApiParam("权限类型") Integer type){
        Assert.isTrue(U.isNotBlank(type),"权限类型不能为空");
        List<Permission> list = permissionService.getByType(type);
        return JsonResult.success("权限列表",PermissionVo.assemblyData(list));
    }

    @ApiMethod("权限信息")
    @GetMapping("/{id}")
    public JsonResult<PermissionVo> info(@PathVariable("id") @ApiParam("权限ID") Long id){
        Assert.isTrue(U.isNotBlank(id),"权限ID不能为空");
        Permission permission = permissionService.getById(id);
        return JsonResult.success("权限信息",PermissionVo.assemblyData(permission));
    }

    @ApiMethod("权限树")
    @GetMapping("/tree")
    public List<PermissionTreeVo> tree(@ApiParam("角色ID") Long roleId){
        Assert.isTrue(U.isNotBlank(roleId),"角色ID不能为空");
        List<Permission> list = permissionService.getByRoleId(roleId);
        List<Permission> all = permissionService.getAll();
        return PermissionTreeVo.assemblyData(list,all);
    }

    @ApiMethod("编辑角色权限")
    @PutMapping("/role/{roleId}")
    public JsonResult role(@PathVariable @ApiParam("角色ID") Long roleId, @RequestBody ListDto listDto){
        Assert.isTrue(U.isNotBlank(roleId),"角色ID不能为空");
        permissionService.updateRolePermission(roleId,listDto.getIds());
        return JsonResult.success("编辑角色权限成功");
    }

    @ApiMethod("新增权限")
    @PostMapping
    public JsonResult save(@RequestBody PermissionDto permissionDto){
        permissionService.save(permissionDto.addPermissionData());
        return JsonResult.success("新增权限成功");
    }

    @ApiMethod("修改权限")
    @PutMapping
    public JsonResult update(@RequestBody PermissionDto permissionDto){
        permissionService.update(permissionDto.updatePermissionData());
        return JsonResult.success("修改权限成功");
    }

    @ApiMethod("删除权限")
    @DeleteMapping
    public JsonResult delete(@RequestBody ListDto listDto){
        Assert.isTrue(A.isNotEmpty(listDto.getIds()),"权限ID不能为空");
        permissionService.delete(listDto.getIds());
        return JsonResult.success("删除权限成功");
    }
}
