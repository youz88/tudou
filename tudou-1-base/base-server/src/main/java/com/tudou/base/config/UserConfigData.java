package com.tudou.base.config;

import com.google.common.collect.Lists;
import com.tudou.common.Const;
import com.tudou.common.resource.CollectHandlerUtil;
import com.tudou.common.resource.CollectResourceUtil;
import com.tudou.common.resource.LoaderHandler;
import com.tudou.common.resource.LoaderResource;
import com.tudou.global.constant.GlobalConst;
import com.tudou.base.constant.UserConst;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.core.io.Resource;

import java.util.List;

/** 商品模块的配置数据. 主要是 mybatis 的多配置目录和类型处理器 */
final class UserConfigData {

    private static final String[] RESOURCE_PATH = new String[] {
            UserConst.MODULE_NAME + "/*.xml",
            UserConst.MODULE_NAME + "-custom/*.xml"
    };
    private static final List<Resource[]> RESOURCES = Lists.newArrayList();
    static {
        RESOURCES.add(LoaderResource.getResourceArray(UserConfigData.class, RESOURCE_PATH));
    }

    private static final List<TypeHandler[]> HANDLERS = Lists.newArrayList();
    static {
        HANDLERS.add(LoaderHandler.getHandleArray(GlobalConst.class, Const.handlerPath(GlobalConst.MODULE_NAME)));
        HANDLERS.add(LoaderHandler.getHandleArray(UserConfigData.class, Const.handlerPath(UserConst.MODULE_NAME)));
    }

    /** 要加载的 mybatis 的配置文件目录 */
    public static final Resource[] RESOURCE_ARRAY = CollectResourceUtil.resource(RESOURCES);
    /** 要加载的 mybatis 类型处理器的目录 */
    public static final TypeHandler[] HANDLER_ARRAY = CollectHandlerUtil.handler(HANDLERS);
}
