package com.tudou.user.model;

import java.io.Serializable;

import com.tudou.user.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 用户 --> base_user */
@Setter
@Getter
@NoArgsConstructor
public class BaseUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> user_id */
    private Long userId;

    /** 帐号 --> user_name */
    private String userName;

    /** 密码MD5(密码+盐) --> password */
    private String password;

    /** 盐 --> salt */
    private String salt;

    /** 昵称 --> nickname */
    private String nickname;

    /** 头像 --> avatar */
    private String avatar;

    /** 电话 --> phone */
    private String phone;

    /** 邮箱 --> email */
    private String email;

    /** 性别(0:女,1:男) --> gender */
    private Gender gender;

    /** 状态(0:正常,1:锁定) --> is_lock */
    private Boolean isLock;

    /** 创建时间 --> create_time */
    private Long createTime;
}