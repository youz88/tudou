package com.tudou.dto;

import com.tudou.common.json.JsonUtil;
import com.tudou.common.util.U;
import com.tudou.user.model.User;
import com.tudou.util.WebPlatformSessionUtil;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.util.Assert;

@Data
public class UserDto {

    @ApiParam("用户ID")
    private Long id;

    @ApiParam("帐号")
    private String username;

    @ApiParam("密码")
    private String password;

    @ApiParam("姓名")
    private String realname;

    @ApiParam("头像")
    private String avatar;

    @ApiParam("电话")
    private String phone;

    @ApiParam("邮箱")
    private String email;

    @ApiParam("性别")
    private Byte sex;

    public User addUserData(){
        User convert = JsonUtil.convert(this, User.class);
        Long userId = WebPlatformSessionUtil.getUserId();
        convert.setCreateId(userId);
        convert.setModifyId(userId);
        return convert;
    }

    public User updateUserData(){
        Assert.isTrue(U.isNotBlank(id),"用户ID不能为空");
        return JsonUtil.convert(this, User.class);
    }
}
