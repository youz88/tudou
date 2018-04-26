package com.tudou.user.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 权限 --> base_permission */
@Setter
@Getter
@NoArgsConstructor
public class BasePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> permission_id */
    private Long permissionId;

    /** 所属上级 --> pid */
    private Long pid;

    /** 名称 --> name */
    private String name;

    /** 类型(1:目录,2:菜单,3:按钮) --> type */
    private Integer type;

    /** 权限值 --> permission_value */
    private String permissionValue;

    /** 路径 --> uri */
    private String uri;

    /** 图标 --> icon */
    private String icon;

    /** 状态(0:禁止,1:正常) --> enable */
    private Boolean enable;

    /** 排序 --> orders */
    private Integer orders;

    /** 创建时间 --> create_time */
    private Long createTime;
}