package com.tudou.base.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tudou.common.util.U;

public enum EnableStatus {

    Disable(0, "禁用"),
    Normal(1, "正常");

    int code;
    String value;
    EnableStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    /** 显示用 */
    public String getValue() {
        return value;
    }
    /** 存进数据库 及 返回给前端 时 */
    @JsonValue
    public int getCode() {
        return code;
    }
    /** 数据反序列化时 */
    @JsonCreator
    public static EnableStatus deserializer(Object obj) {
        return U.toEnum(EnableStatus.class, obj);
    }
}
