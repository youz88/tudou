package com.tudou.user.model;

import lombok.Data;

import java.io.Serializable;

/** 权限 --> base_permission */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> id */
    private Long id;

    /** 所属上级 --> pid */
    private Long pid;

    /** 名称 --> name */
    private String name;

    /** 类型(1:目录,2:菜单,3:按钮) --> type */
    private Byte type;

    /** 权限方法(get,head,post,put,delete) --> method */
    private String method;

    /** 路径 --> url */
    private String url;

    /** 图标 --> icon */
    private String icon;

    /** 状态(0:禁止,1:正常) --> status */
    private Byte status;

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