package com.tudou.base.model;

import lombok.Data;

import java.io.Serializable;

/** 操作日志 --> base_operation_log */
@Data
public class OperationLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号 --> id */
    private Long id;

    /** 操作描述 --> description */
    private String description;

    /** 操作用户 --> username */
    private String username;

    /** 操作时间 --> start_time */
    private Long startTime;

    /** 消耗时间 --> spend_time */
    private Integer spendTime;

    /** 根路径 --> base_path */
    private String basePath;

    /** URI --> uri */
    private String uri;

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
}