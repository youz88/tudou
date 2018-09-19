package com.tudou.user.model;

import java.io.Serializable;

/** 操作日志 --> base_operation_log */
public class OperationLogWithBLOBs extends OperationLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String parameter;

    private String result;
}