package com.tudou.user.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 角色 --> base_role */
@Setter
@Getter
@NoArgsConstructor
public class BaseRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> role_id */
    private Long roleId;

    /** 角色名称 --> name */
    private String name;

    /** 角色描述 --> description */
    private String description;

    /** 排序 --> orders */
    private Integer orders;

    /** 创建时间 --> create_time */
    private Long createTime;
}