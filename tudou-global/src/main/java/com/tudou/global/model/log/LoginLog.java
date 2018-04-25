package com.tudou.global.model.log;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tudou.common.util.U;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *用户的登录日志
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class LoginLog extends BaseBusinessLog {

    private SubSystem subSystem;
    private static final long serialVersionUID = 5306208618366605334L;

    /**
     * 登录的子系统
     */
    public enum SubSystem {
        Manager(1, "中控"),
        Distributor(2, "总代理系统"),
        Vendor(3, "工厂系统"),
        Rural (4, "农村电商"),
        School(5, "校园电商"),
        SuperMarket(6, "商超");
        int code;
        String value;
        SubSystem(int code, String value) {
            this.code = code;
            this.value = value;
        }


        public String getValue() {
            return value;
        }

        @JsonValue
        public int getCode() {
            return code;
        }


        @JsonCreator
        public static SubSystem deserializer(Object obj) {
            return U.toEnum(SubSystem.class, obj);
        }
    }
}
