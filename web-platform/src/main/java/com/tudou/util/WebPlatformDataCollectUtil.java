package com.tudou.util;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tudou.common.Const;
import com.tudou.common.resource.Loader;
import com.tudou.common.util.A;
import com.tudou.common.util.U;
import com.tudou.global.constant.GlobalConst;
import com.tudou.user.constant.UserConst;

import java.util.List;
import java.util.Map;
import java.util.Set;

/** 从各模块中收集数据的工具类 */
public final class WebPlatformDataCollectUtil {

    private static final List<Class[]> ALL_MODEL_ENUM = Lists.newArrayList();
    static {
        Map<String, Class> enumMap = A.maps(
                GlobalConst.MODULE_NAME, GlobalConst.class,

                UserConst.MODULE_NAME, UserConst.class

        );
        enumMap.forEach((key, value) -> ALL_MODEL_ENUM.add(Loader.getEnumArray(value, Const.enumPath(key))));
    }

    /** 放入渲染上下文中去的枚举列表 */
    public static final Class[] ENUM_CLASS;
    /** 将所有的枚举整成一个 map 供接口调用 */
    public static final Map<String, Object> ENUMS = Maps.newHashMap();
    static {
        Set<Class> set = Sets.newHashSet();
        for (Class[] enums : ALL_MODEL_ENUM) {
            for (Class anEnum : enums) {
                if (U.isNotBlank(anEnum) && anEnum.isEnum()) {
                    // 将每个模块里面的枚举都收集起来, 然后会放入到渲染上下文里面去
                    set.add(anEnum);
                    // 将每个模块里面的枚举都收集起来供请求接口使用
                    ENUMS.put(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, anEnum.getSimpleName()), anEnum);
                }
            }
        }
        ENUM_CLASS = set.toArray(new Class[set.size()]);
    }
}
