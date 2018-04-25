package com.tudou.global.model.log;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/**
 * 日志类型比较多，为了统一做成一个队列，需要区分出来
 * 所以在内部做了一个包装，发送的时候发送这个类型，
 * 在接受信息的时候再拆分
 *
 * @param <T>
 */
@Setter
@Getter
@NoArgsConstructor
public  class TudouLogContainer<T> implements Serializable {

    private TudouLogType logType;

    private T logContent;



}
