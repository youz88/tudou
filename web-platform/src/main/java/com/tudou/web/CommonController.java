package com.tudou.web;

import com.github.liuanxin.api.annotation.ApiGroup;
import com.github.liuanxin.api.annotation.ApiMethod;
import com.github.liuanxin.api.annotation.ApiParam;
import com.tudou.common.util.SecurityCodeUtil;
import com.tudou.util.WebPlatformSessionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ApiGroup("公共接口")
@RestController
public class CommonController {

    @ApiMethod("获取验证码")
    @GetMapping("/code")
    public void code(HttpServletResponse response,
                     @ApiParam("宽度") String width,
                     @ApiParam("高度") String height,
                     @ApiParam("数字个数") String count,
                     @ApiParam("图片上的文字: 英文, 数字(num), 英文加数字(n)还是中文(cn), 传空值或传的值不是 num n cn 则会默认是英文") String style) throws IOException {
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
