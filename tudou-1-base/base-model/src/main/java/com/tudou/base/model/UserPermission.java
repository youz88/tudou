package com.tudou.base.model;

import lombok.Data;

import java.io.Serializable;

/** 用户权限关联表 --> base_user_permission */
@Data
public class UserPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> user_permission_id */
    private Long userPermissionId;

    /** 用户编号 --> user_id */
    private Long userId;

    /** 权限编号 --> permission_id */
    private Long permissionId;

    /** 权限类型(-1:减权限,1:增权限) --> type */
    private Integer type;
}