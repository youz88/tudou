package com.tudou.base.service;

import com.tudou.base.model.Permission;
import com.tudou.common.Page;
import com.tudou.common.PageInfo;

import java.util.List;

public interface PermissionService {

    /** 分页查询权限列表 */
    PageInfo<Permission> listByParam(Permission permission, Page page);

    /** 保存权限 */
    int save(Permission permission);

    /** 更新权限 */
    int update(Permission permission);

    /** 删除权限 */
    int delete(List<Long> ids);

    /** 通过ID查询 */
    Permission getById(Long permissionId);

    /** 通过权限类型查询 */
    List<Permission> getByType(Integer type);

    /** 通过角色查询 */
    List<Permission> getByRoleId(Long roleId);

    /** 查询所有权限列表 */
    List<Permission> getAll();

    /** 编辑角色权限 */
    void updateRolePermission(Long roleId, List<Long> permissionIds);
}
