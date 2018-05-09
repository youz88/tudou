package com.tudou.user.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 用户角色关联表 --> base_user_role */
@Setter
@Getter
@NoArgsConstructor
public class BaseUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> id */
    private Long id;

    /** 用户编号 --> user_id */
    private Long userId;

    /** 角色编号 --> role_id */
    private Long roleId;
}