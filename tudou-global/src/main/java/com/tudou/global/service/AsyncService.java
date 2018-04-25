package com.tudou.global.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

/** 当前类上的方法, 在其他类调用时, 都会异步运行 */
@Async
@Configuration
public class AsyncService {

    /** 异步发送短信 */
    public Future<Boolean> sendSms(String phone, String sms) {
        // todo ...
        return new AsyncResult<>(true);
    }
}
