package com.tudou.config;

import com.google.common.collect.Lists;
import com.tudou.common.Const;
import com.tudou.common.annotation.NotNeedLogin;
import com.tudou.common.annotation.NotNeedPermission;
import com.tudou.common.util.A;
import com.tudou.common.util.LogUtil;
import com.tudou.common.util.RequestUtils;
import com.tudou.common.util.U;
import com.tudou.util.WebPlatformSessionUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.List;

/** 此拦截器要放在拦截器链的最前面 */
public class WebPlatformInterceptor implements HandlerInterceptor {

    private static final List<String> LET_IT_GO = Lists.newArrayList("/error");
    private static final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String METHODS = "Access-Control-Allow-Methods";
    private static final String HEADERS = "Access-Control-Allow-Headers";
    /** for ie: https://www.lovelucy.info/ie-accept-third-party-cookie.html */
//    private static final String P3P = "P3P";

    private boolean online;
    private boolean permission;
    WebPlatformInterceptor(boolean online, boolean permission) {
        this.online = online;
        this.permission = permission;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        handlerCors(request, response);
        bindParam();
        checkLoginAndPermission(handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        if (ex != null) {
            if (LogUtil.ROOT_LOG.isDebugEnabled()) {
                LogUtil.ROOT_LOG.debug("request was over, but have exception: " + ex.getMessage());
            }
        }
        unbindParam();
    }

    private void handlerCors(HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader(HttpHeaders.ORIGIN);
        if (U.isNotBlank(origin)) {
            if (U.isBlank(response.getHeader(ALLOW_ORIGIN))) {
                response.addHeader(ALLOW_ORIGIN, origin);
            }
            if (U.isBlank(response.getHeader(CREDENTIALS))) {
                response.addHeader(CREDENTIALS, "true");
            }
            if (U.isBlank(response.getHeader(METHODS))) {
                response.addHeader(METHODS, A.toStr(Const.SUPPORT_METHODS));
            }
            if (U.isBlank(response.getHeader(HEADERS))) {
                response.addHeader(HEADERS, "Accept, Accept-Encoding, Accept-Language, Cache-Control, " +
                        "Connection, Cookie, DNT, Host, User-Agent, Content-Type, Authorization, " +
                        "X-Requested-With, Origin, Access-Control-Request-headers");
            }
//            if (RequestUtils.userAgent().toUpperCase().contains("MSIE") && U.isBlank(response.getHeader(P3P))) {
//                response.addHeader(P3P, "CP='CAO IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
//            }
        }
    }

    private void bindParam() {
        // 打印日志上下文中的数据
        LogUtil.RequestLogContext logContextInfo = RequestUtils.logContextInfo()
                .setId(String.valueOf(WebPlatformSessionUtil.getUserId()))
                .setName(WebPlatformSessionUtil.getUserName());
        LogUtil.bind(logContextInfo);
    }

    private void unbindParam() {
        // 删除打印日志上下文中的数据
        LogUtil.unbind();
    }

    /** 检查登录及权限 */
    private void checkLoginAndPermission(Object handler) {
        if (!online) {
            return;
        }

        if (LET_IT_GO.contains(RequestUtils.getRequest().getRequestURI())) {
            return;
        }
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 在不需要登录的 url 上标注 @NotNeedLogin
        NotNeedLogin notNeedLogin = getAnnotation(handlerMethod, NotNeedLogin.class);
        // 标注了 NotNeedLogin 且 flag 为 true(默认就是 true)则表示当前的请求不需要验证登录
        if (notNeedLogin != null && notNeedLogin.flag()) {
            return;
        }

        // 检查登录
        WebPlatformSessionUtil.checkLogin();

        // 超级管理员忽略权限检查
        if (WebPlatformSessionUtil.isSuper()) {
            return;
        }
        // 全局设置了不验证权限则放过
        if (!permission) {
            return;
        }

        // 在不需要验证权限的 url 上标注 @NotNeedPermission
        NotNeedPermission notNeedPermission = getAnnotation(handlerMethod, NotNeedPermission.class);
        // 标注了 NotNeedPermission 且 flag 为 true(默认就是 true)则表示当前的请求不需要验证权限
        if (notNeedPermission != null && notNeedPermission.flag()) {
            return;
        }

        // 检查权限
        WebPlatformSessionUtil.checkPermission();
    }
    private <T extends Annotation> T getAnnotation(HandlerMethod handlerMethod, Class<T> clazz) {
        // 先找方法上的注解, 再找类上的注解
        T annotation = handlerMethod.getMethodAnnotation(clazz);
        return annotation == null ? handlerMethod.getBean().getClass().getAnnotation(clazz) : annotation;
    }
}
