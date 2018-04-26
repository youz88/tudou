package com.tudou.user.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 用户权限关联表 --> base_user_permission */
@Setter
@Getter
@NoArgsConstructor
public class BaseUserPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> user_permission_id */
    private Long userPermissionId;

    /** 用户编号 --> user_id */
    private Long userId;

    /** 权限编号 --> permission_id */
    private Long permissionId;
}