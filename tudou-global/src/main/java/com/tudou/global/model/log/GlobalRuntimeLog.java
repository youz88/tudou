package com.tudou.global.model.log;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;


/**
系统运行日志，包括异常的错误日志
 */
@Setter
@Getter
@NoArgsConstructor
public class GlobalRuntimeLog  implements Serializable {


    /**
     * 记录日志的格式，包含多种信息
     */
    private static final long serialVersionUID = 6855998764713441586L;

    /**
     * 在ES中的log唯一编号
     */
    private String logCode ;
    private Long logId;


    /**
     * 调用的顺序，每进入一个就将这个加1
     */
    private int rpcSort;
    /**
     *  调用的 服务名称
     */

    private String serviceName;

    /**
     * 调用者的ip
     */
    private String callerIpAddress;

    /**
     * 调用者的端口
     */
    private Integer callerPort;

    /**
     * 调用者的ip
     */
    private String serviceIpAddress;
    /**
     * 调用者的端口
     */
    private Integer servicePort;

    /**
     * 调用开始时间
     */
    private String startTime;

    /**
    调用开始时间
     */
    private String endTime;

    /**
     * 调用时间 毫秒
     */
    private Long processTime;

    /**
     * 保存一些本次调用的一些信息，比如参数信息
     */
    private String requestContent;

    /**
     * 调用结果
     */
    private String responseResult;

    /**
     * 日志的产生时间
     */
    private String logTime;

    /**
     * 本次操作的结果
     */
    private Boolean result;


}
