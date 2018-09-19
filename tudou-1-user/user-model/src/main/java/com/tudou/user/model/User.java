package com.tudou.user.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/** 用户 --> base_user */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID --> id */
    private Long id;

    /** 帐号 --> username */
    private String username;

    /** 密码MD5(密码+盐) --> password */
    private String password;

    /** 盐 --> salt */
    private String salt;

    /** 姓名 --> realname */
    private String realname;

    /** 头像 --> avatar */
    private String avatar;

    /** 电话 --> phone */
    private String phone;

    /** 邮箱 --> email */
    private String email;

    /** 性别 --> sex */
    private Byte sex;

    /** 状态(0:正常,1:锁定) --> locked */
    private Byte locked;

    /** 创建时间 --> create_time */
    private Long createTime;

    /** 创建用户ID --> create_id */
    private Long createId;

    /** 修改时间 --> modify_time */
    private Long modifyTime;

    /** 修改用户ID --> modify_id */
    private Long modifyId;
}