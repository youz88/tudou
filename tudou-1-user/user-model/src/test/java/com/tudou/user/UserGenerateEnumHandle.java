package com.tudou.user;

import com.tudou.common.Const;
import com.tudou.common.util.GenerateEnumHandle;
import com.tudou.user.constant.UserConst;
import org.junit.Test;

public class UserGenerateEnumHandle {

    @Test
    public void generate() {
        GenerateEnumHandle.generateEnum(getClass(), Const.BASE_PACKAGE, UserConst.MODULE_NAME);
    }
}
