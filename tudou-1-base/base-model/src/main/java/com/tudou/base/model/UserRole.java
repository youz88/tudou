package com.tudou.base.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/** 用户角色关联表 --> base_user_role */
@Data
@Accessors(chain = true)
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> user_role_id */
    private Long userRoleId;

    /** 用户编号 --> user_id */
    private Long userId;

    /** 角色编号 --> role_id */
    private Long roleId;
}