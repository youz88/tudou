package com.tudou.user.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 操作日志 --> base_log */
@Setter
@Getter
@NoArgsConstructor
public class BaseLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> log_id */
    private Long logId;

    /** 操作描述 --> description */
    private String description;

    /** 操作用户 --> user_name */
    private String userName;

    /** 操作用户ID --> user_id */
    private Long userId;

    /** 操作时间 --> start_time */
    private Long startTime;

    /** 消耗时间 --> spend_time */
    private Integer spendTime;

    /** URL --> url */
    private String url;

    /** 请求类型 --> method */
    private String method;

    /** 用户标识 --> user_agent */
    private String userAgent;

    /** IP地址 --> ip */
    private String ip;

    /** 权限值 --> permissions */
    private String permissions;

    private String parameter;
}