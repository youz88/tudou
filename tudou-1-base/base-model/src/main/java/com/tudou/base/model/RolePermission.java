package com.tudou.base.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/** 角色权限关联表 --> base_role_permission */
@Data
@Accessors(chain = true)
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> role_permission_id */
    private Long rolePermissionId;

    /** 角色编号 --> role_id */
    private Long roleId;

    /** 权限编号 --> permission_id */
    private Long permissionId;
}