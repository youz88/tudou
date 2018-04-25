package com.tudou.global.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tudou.common.util.U;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class BaseMessage {

    private Integer id;
    //信息标题
    private  String title;
    //信息内容
    private  String content;
    //信息发送者
    private  String sender;
    //信息接受者
    private  String receiver;
    /** 发送方式 1短信，2邮件，3im,4站内提醒 */
    private  SendMode otype;
    //发送状态 (0未发送,1已发送)
    private  Integer status;

    private Date createTime;

    private  Date sendTime;
    /** IM自定义消息类型,或短信类型:1验证码类,2生产类,3服务类,4互动类,5通知类,6营销类 */
    private  String mtype;
    //发送次数
    private  Integer times;

    /** 发送结果 从第三方获取，但是不确定类型，所以类型为string */
    private String result;

    public static enum SendMode {
        Sms(1, "短信"),
        Email(2, "邮件"),
        Im(3, "即时通信"),
        Msg(4, "站内消息"),
        Notice(5,"IM通知");
        int code;
        String value;
        SendMode(int code, String value) {
            this.code = code;
            this.value = value;
        }

        /** 显示用 */
        public String getValue() {
            return value;
        }
        /** 存进数据库时 */
        @JsonValue
        public int getCode() {
            return code;
        }
        /** 数据反序列化时调用 */
        @JsonCreator
        public static SendMode deserializer(Object obj) {
            return U.toEnum(SendMode.class, obj);
        }
        public boolean isSms() {
            return this == Sms;
        }
        public boolean isEmail() {
            return this == Email;
        }
        public boolean isIm() {
            return this == Im;
        }
        public boolean isMsg() {
            return this == Msg;
        }
    }
}
