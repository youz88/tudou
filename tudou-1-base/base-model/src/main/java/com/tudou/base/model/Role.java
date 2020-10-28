package com.tudou.base.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/** 角色 --> base_role */
@Data
@Accessors(chain = true)
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> id */
    private Long id;

    /** 角色名称 --> name */
    private String name;

    /** 角色标题 --> title */
    private String title;

    /** 角色描述 --> description */
    private String description;

    /** 排序 --> orders */
    private Long orders;

    /** 创建时间 --> create_time */
    private Long createTime;

    /** 创建用户ID --> create_id */
    private Long createId;

    /** 修改时间 --> modify_time */
    private Long modifyTime;

    /** 修改用户ID --> modify_id */
    private Long modifyId;
}