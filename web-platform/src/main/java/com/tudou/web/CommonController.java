package com.tudou.web;

import com.tudou.common.util.SecurityCodeUtil;
import com.tudou.util.WebPlatformSessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "公共接口")
@RestController
public class CommonController {

    @ApiOperation("获取验证码")
    @GetMapping("/code")
    public void code(HttpServletResponse response, String width, String height,
                     String count, String style) throws IOException {
        SecurityCodeUtil.Code code = SecurityCodeUtil.generateCode(count, style, width, height);

        // 往 session 里面丢值
        WebPlatformSessionUtil.putImageCode(code.getContent());

        // 向页面渲染图像
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        ImageIO.write(code.getImage(), "jpeg", response.getOutputStream());
    }
}
