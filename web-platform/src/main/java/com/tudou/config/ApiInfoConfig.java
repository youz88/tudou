package com.tudou.config;

import com.github.liuanxin.api.annotation.EnableApiInfo;
import com.github.liuanxin.api.model.DocumentCopyright;
import com.github.liuanxin.api.model.DocumentResponse;
import com.tudou.common.PageInfo;
import com.tudou.common.json.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@EnableApiInfo
public class ApiInfoConfig {

    public static final String PROJECT_TITLE = "土豆";

    public static final String PROJECT_CONTACT = "youz88";

    public static final String PROJECT_VERSION = "1.0.0";

    public static final String PROJECT_COPYRIGHT = "GPL V3";

    // 可以在不同的 profile 中设置不同的值, 如:
    // application.yml      => online: false
    // application-test.yml => online: false
    // application-prod.yml => online: true
    @Value("${online:false}")
    private boolean online;

    @Bean
    public DocumentCopyright apiCopyright() {
        return new DocumentCopyright(PROJECT_TITLE, PROJECT_CONTACT, PROJECT_VERSION, PROJECT_COPYRIGHT)
                .setIgnoreUrlSet(ignoreUrl())
                .setGlobalResponse(globalResponse())
                // // true 则不进行文档收集, 默认是 false
                .setOnline(online)
                // // 当某些接口不好标 @ApiIgnore 时(格式: url|method, url 可以使用 * 通配 method 可以忽略)
                // .setIgnoreUrlSet(Sets.newHashSet("/user*", "/product/info|post"))

                // // 全局的响应说明, 如果类或方法上有 @ApiResponses 则以它们为准
                // .setGlobalResponse(Arrays.asList(
                //     new DocumentResponse(400, "参数有误"),
                //     new DocumentResponse(500, "请求异常").setResponse(XXX.class) // 见 @ApiReturnType 示例说明
                // ))

                // // 全局 token, 这个配置会生成在每个接口的参数中,
                // // 如果想在具体的接口上忽略这些配置 或者 设置不同的参数 请使用 @ApiTokens 注解
                // .setGlobalTokens(Arrays.asList(
                //     DocumentParam.buildToken("x-token", "认证数据", "abc", ParamType.Header).setHasTextarea("1"),
                //     DocumentParam.buildToken("x-version", "接口版本", "1.0.0", ParamType.Query).setMust("1")
                // ))

                // // 字段说明是否输出在返回示例中, 不设置则默认是 true,
                // // 设置为 false 将会单独罗列, 方法上标了则以方法上的为准
                // .setCommentInReturnExample(false)


                // // 用在多文档收集, 是否将项目合并后输出, 默认是 false
                // //   如果为 true, 请务必保证所有项目的全局响应说明和全局 token 是一致的,
                // //   附加在一起并去重(当前做法)可能会导致文档错误;
                // //   -----
                // //   如果是 false, 将在页面上选择项目进行请求, 请务必保证设置的项目都开启了 cors,
                // //   否则将会因为跨域问题导致无法访问
                // .setProjectMerge(true)

                // // 收集其他项目的文档
                // .setProjectMap(new LinkedHashMap<String, String>() {{
                //         // key 格式: 名称-说明, 如: user-用户. value 是项目地址, 如: http://ip:port
                //         put("user-用户", "http://ip:port/user");
                //         put("product-商品", "http://ip:port/product");
                // }})
                ;
    }

    private List<DocumentResponse> globalResponse() {
        /*
        List<DocumentResponse> responseList = new ArrayList<>();
        for (JsonCode code : JsonCode.values()) {
            responseList.add(new DocumentResponse(code.getCode(), code.getValue()));
        }
        */
        List<DocumentResponse> responseList = new ArrayList<>();
        responseList.add(new DocumentResponse(1, "success"));
        return responseList;
    }

    private Set<String> ignoreUrl() {
        Set<String> urlSet = new HashSet<>();
        urlSet.add("/error");
        return urlSet;
    }
}
