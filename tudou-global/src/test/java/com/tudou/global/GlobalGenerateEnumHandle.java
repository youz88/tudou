package com.tudou.global;

import com.tudou.common.Const;
import com.tudou.common.util.GenerateEnumHandle;
import com.tudou.global.constant.GlobalConst;
import org.junit.Test;

public class GlobalGenerateEnumHandle {

    @Test
    public void generate() {
        GenerateEnumHandle.generateEnum(getClass(), Const.BASE_PACKAGE, GlobalConst.MODULE_NAME);
    }
}
