package com.tudou.common;


import com.tudou.common.util.U;

import java.math.BigDecimal;

/** 项目中会用到的常量 */
public final class Const {

    // ========== load ==========
    /** 当前项目的基本包名 */
    public static final String BASE_PACKAGE = "com.wolianw.erp";
    /** 指定模块存放枚举的包名 */
    public static String enumPath(String moduleName) {
        return BASE_PACKAGE + "." + moduleName.replace('-', '.') + ".enums";
    }
    /** 指定模块存放 handler 的包名 */
    public static String handlerPath(String moduleName) {
        return BASE_PACKAGE + "." + moduleName.replace('-', '.') + ".handler";
    }
    /** 指定模块存放 model 的包名 */
    public static String modelPath(String moduleName) {
        return BASE_PACKAGE + "." + moduleName.replace('-', '.') + ".model";
    }
    // ========== load ==========

    // ========== dubbo ==========
    /** 全局的 dubbo 版本 */
    public static final String DUBBO_VERSION = "1.0";
    /** 全局的 dubbo 超时时间, 单位: 毫秒 */
    public static final int DUBBO_TIMEOUT = 15 * 1000;
    /** 全局的 dubbo 配置 */
    public static final String DUBBO_FILTER = "";
    // ========== dubbo ==========

    private static final int SCALE = 2;
    /** 设置金额的精度. 保留两位小数点 */
    public static BigDecimal precision(BigDecimal money) {
        // + ==> add
        // - ==> subtract
        // * ==> multiply
        // / ==> divide 除法调用下面的方法
        return U.isBlank(money) ? money : money.setScale(SCALE, BigDecimal.ROUND_HALF_EVEN);
    }
    /** 当金额为空时返回的默认金额 */
    public static BigDecimal nilMoney() {
        return precision(BigDecimal.ZERO);
    }
    /** 在金额上操作除法 */
    public static BigDecimal divide(BigDecimal divisor, BigDecimal dividends) {
        if (U.isNotBlank(divisor) && U.isNotBlank(dividends)) {
            return divisor.divide(dividends, SCALE, BigDecimal.ROUND_UP);
        } else {
            return divisor;
        }
    }
    /** 在金额上做向上取整. 如: 2.451 返回 2.46 */
    public static BigDecimal handleMoney(BigDecimal money) {
        return U.isBlank(money) ? money : money.setScale(SCALE, BigDecimal.ROUND_UP);
    }

    /**
     * <pre>
     * 在 servlet 规范中有 forward 和 redirect 两种页面跳转.
     *   forward 不会改变页面的请求地址, 而且前一个请求的 request 和 response 在下一个请求中还有效.
     *   redirect 正好不同, 要传值得使用 参数拼接 或者放在 session 里(都有利弊, 建议使用前者)
     *
     * 在 spring mvc 的 controller 上返回 String 时
     *   return "forward:/some/one" => 转发到 /some/one 的 controller 方法上去. mvc 内部的异常处理就是基于这种方式
     *   return "some/one"          => 转发到 template-path/some/one.jsp 页面去(如果是 jsp 的话)
     *   return "/some/one"         => 同 "some/one"
     * </pre>
     */
    public static final String FORWARD_PREFIX = "forward:";
    /**
     * <pre>
     * 在 servlet 规范中有 forward 和 redirect 两种页面跳转.
     *   forward 不会改变页面的请求地址, 而且前一个请求的 request 和 response 在下一个请求中还有效.
     *   redirect 正好不同, 要传值得使用 参数拼接 或者放在 session 里(都有利弊, 建议使用前者)
     *
     * 要传递参数, 可以使用 RedirectAttributes 或者直接拼在 url 上. 使用 spring mvc 的 redirect 会将当前上下文内容拼在 url 中
     * </pre>
     * @see org.springframework.web.servlet.mvc.support.RedirectAttributes
     */
    public static final String REDIRECT_PREFIX = "redirect:";

    /** pc 端传过来的 token 的 key, 下划线主要用来区分跟其他参数不同 */
    public static final String TOKEN = "_token";

    /** cors 支持的所有方法 */
    public static final String[] SUPPORT_METHODS = new String[] { "HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS" };
}
