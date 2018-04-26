package com.tudou.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tudou.common.util.U;

public enum Gender {

    Man(0,"女"),
    Woman(1,"男");

    int code;
    String value;
    Gender(int code, String value) {
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
    public static Gender deserializer(Object obj) {
        return U.toEnum(Gender.class, obj);
    }

    public boolean isMan(){
        return this == Man;
    }

    public boolean isWoman(){
        return this == Woman;
    }
}
