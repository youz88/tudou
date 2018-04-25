package com.tudou.global.config;

import com.tudou.global.constant.GlobalConst;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@Configuration
@ConditionalOnClass({ ActiveMQQueue.class, JmsTemplate.class, Queue.class })
public class QueueConfig {

    @Value("${spring.activemq.sessionCacheSize:1}")
    private int mqSessionCacheSize;

    @Autowired
    private ActiveMQConnectionFactory activeMQConnectionFactory;

    @Bean
    public JmsTemplate jmsTemplate() {
        CachingConnectionFactory factory = new CachingConnectionFactory(activeMQConnectionFactory);
        factory.setSessionCacheSize(mqSessionCacheSize);
        return new JmsTemplate(factory);
    }

    // 使用 CacheConnectionFactory 再封装一层 ActiveMQConnectionFactory
    @Bean(GlobalConst.TUDOU_LOG_MQ_NAME)
    public Queue tudouLogQueue() {
        return new ActiveMQQueue(GlobalConst.TUDOU_LOG_MQ_NAME);
    }


    @Bean(GlobalConst.TUDOU_MESSAGE_MQ_NAME)
    public Queue tudouQueue() {
        return new ActiveMQQueue(GlobalConst.TUDOU_MESSAGE_MQ_NAME);
    }
}
