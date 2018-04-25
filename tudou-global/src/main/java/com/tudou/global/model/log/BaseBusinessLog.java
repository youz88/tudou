package com.tudou.global.model.log;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 各种业务日志
 订单、商品 ，退款、退货、各种状态的变更日志

 */
@Setter
@Getter
@NoArgsConstructor
class BaseBusinessLog implements Serializable {

    /**
     * 预留，在数据库中的唯一ID
     */
    private Long logId;

    /**
     * 在ES中的log唯一编号
     */
    private String logCode ;

    /**
     * 日志的产生时间
     */
    private String logTime;

    /**
     * 本次操作的操作人
     */
    private Long operatorId;

    /**
     * 本次操作的操作人
     */
    private String operatorName;

    /**
     * 本次日志的描述信息
     */
    private String logInfo;

    /**
     * 本次操作的IP地址
     */
    private String ipAddress;

    /**
     * 本次操作的结果
     */
    private Boolean result;


}
