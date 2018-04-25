package com.tudou.global.service;

import com.tudou.global.config.QueueConfig;
import com.tudou.global.constant.GlobalConst;
import com.tudou.global.model.BaseMessage;
import com.tudou.global.model.log.*;
import com.tudou.common.json.JsonUtil;
import com.tudou.common.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@Configuration
@ConditionalOnClass({ JmsTemplate.class, Queue.class })
public class MessagePushService {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * @see QueueConfig#tudouLogQueue
     */
    @Autowired
    @Qualifier(GlobalConst.TUDOU_LOG_MQ_NAME)
    private Queue tudouLogQueue;

    /**
     * @see QueueConfig#tudouQueue
     */
    @Autowired
    @Qualifier(GlobalConst.TUDOU_MESSAGE_MQ_NAME)
    private Queue tudouQueue;

    /**
     * 发送系统消息日志
     */
    public void sendRuntimeLog(GlobalRuntimeLog runtimelog) {
        this.sendLog(runtimelog);
    }

    /**
     * 发送登录日志
     */
    public void sendLoginLog(LoginLog loginlog) {
        this.sendLog(loginlog);
    }


    /**
     * 日志属于不需要实时处理的记录
     * 所以为了减少为MQ服务器的队列的监听，合并为同一个队列，按照不同的类型来进行操作
     */
    private <T> void sendLog(T log) {
        if (U.isBlank(log)) {
            return;
        }
        TudouLogContainer<T> container = new TudouLogContainer<>();
        if (log instanceof GlobalRuntimeLog) {
            container.setLogType(TudouLogType.GlobalRuntime);
        } else if (log instanceof LoginLog) {
            container.setLogType(TudouLogType.Login);
        } else {
            return;
        }
        container.setLogContent(log);
        jmsTemplate.convertAndSend(tudouLogQueue, JsonUtil.toJson(container));

    }

    /** 向队列发布消息 */
    public void sendMessage(BaseMessage message) {
        if (U.isBlank(message)) {
            return;
        }

        jmsTemplate.convertAndSend(tudouQueue, JsonUtil.toJson(message));
    }
}
