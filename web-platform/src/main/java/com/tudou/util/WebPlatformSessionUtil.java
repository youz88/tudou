package com.tudou.util;


import com.tudou.common.exception.ForbiddenException;
import com.tudou.common.exception.NotLoginException;
import com.tudou.common.util.RequestUtils;
import com.tudou.common.util.U;
import com.tudou.base.model.Permission;
import com.tudou.base.model.User;

import java.util.List;

/** !!! 操作 session 都基于此, 其他地方不允许操作! 避免 session 被滥用 !!! */
public class WebPlatformSessionUtil {

    /** 放在 session 里的图片验证码 key */
    private static final String CODE = WebPlatformSessionUtil.class.getName() + ".CODE";
    /** 放在 session 里的用户 的 key */
    private static final String USER = WebPlatformSessionUtil.class.getName() + ".USER";

    /** 验证图片验证码 */
    public static boolean checkImageCode(String code) {
        if (U.isBlank(code)) {
            return false;
        }

        Object securityCode = RequestUtils.getInSession(CODE);
        return securityCode != null && code.equalsIgnoreCase(securityCode.toString());
    }
    /** 将图片验证码的值放入 session */
    public static void putImageCode(String code) {
        RequestUtils.putInSession(CODE, code);
    }

    /** 登录之后调用此方法, 主要就是将 用户信息、可访问的 url 等放入 session */
    public static void whenLogin(User account, List<Permission> permissionList) {
        WebPlatformSessionModel sessionModel = WebPlatformSessionModel.assemblyData(
                getSessionInfo(), RequestUtils.getDomain(), account,permissionList
        );
        RequestUtils.putInSession(USER, sessionModel);
    }
    /** 从 session 中获取用户信息 */
    private static WebPlatformSessionModel getSessionInfo() {
        Object session = RequestUtils.getInSession(USER);
        return (session instanceof WebPlatformSessionModel) ? (WebPlatformSessionModel) session : null;
    }

    /** 获取用户信息. 没有则使用默认信息 */
    private static WebPlatformSessionModel getSessionInfoWithDefault() {
        WebPlatformSessionModel sessionModel = getSessionInfo();
        return sessionModel == null ? WebPlatformSessionModel.defaultUser() : sessionModel;
    }

    /** 从 session 中获取用户 id */
    public static Long getUserId() {
        return getSessionInfoWithDefault().getId();
    }

    /** 从 session 中获取用户名 */
    public static String getUserName() {
        return getSessionInfoWithDefault().getName();
    }

    /** 从 session 中获取父账户ID(如果该用户为非子账户,则取userID) */
    public static Long getParentId() {
        Long parentUserId = getSessionInfoWithDefault().getParentId();
        return U.isBlank(parentUserId) ? getUserId() : parentUserId;
    }

    /** 是否是超级管理员, 是则返回 true */
    public static boolean isSuper() {
        return getSessionInfoWithDefault().wasSuper();
    }

    /** 验证用户是否有登录, 如果有则返回 true */
    public static boolean hasLogin() {
        return getSessionInfoWithDefault().wasLogin(RequestUtils.getDomain());
    }
    /** 验证登录, 未登录则抛出异常 */
    public static void checkLogin() {
        if (!hasLogin()) {
            throw new NotLoginException();
        }
    }

    /** 是否有访问权限, 有则返回 true */
    public static boolean hasPermission() {
        // 没有登录当然也就表示没有权限了
        checkLogin();
        // 管理员直接放过权限检查
        if (isSuper()) {
            return true;
        }

        String url = RequestUtils.getRequest().getRequestURI();
        String method = RequestUtils.getRequest().getMethod();
        return getSessionInfoWithDefault().hasPermission(RequestUtils.getDomain(), url, method);
    }

    /** 检查权限, 无权限则抛出异常 */
    public static void checkPermission() {
        if (!hasPermission()) {
            throw new ForbiddenException("您没有(" + RequestUtils.getRequest().getRequestURL().toString() + ")的访问权限");
        }
    }

    /** 退出登录时调用. 清空 session */
    public static void signOut() {
        RequestUtils.clearSession();
    }

    public static void logout() {
        RequestUtils.clearSession();
    }
}
