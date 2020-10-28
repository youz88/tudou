package com.tudou.dto;

import com.github.liuanxin.api.annotation.ApiParam;
import com.tudou.base.enums.EnableStatus;
import com.tudou.base.model.User;
import com.tudou.common.json.JsonUtil;
import com.tudou.common.util.U;
import com.tudou.util.WebPlatformSessionUtil;
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

    @ApiParam("昵称")
    private String nickname;

    @ApiParam("头像")
    private String avatar;

    @ApiParam("电话")
    private String phone;

    @ApiParam("邮箱")
    private String email;

    @ApiParam("性别")
    private Integer gender;

    public User addUserData(){
        Assert.isTrue(U.isNotBlank(username),"用户名不能为空");
        Assert.isTrue(U.isNotBlank(password),"密码不能为空");
        if (U.isBlank(nickname)) {
            nickname = username;
        }
        User convert = JsonUtil.convert(this, User.class);
        Long userId = WebPlatformSessionUtil.getUserId();
        convert.setCreateId(userId)
                .setModifyId(userId)
                .setStatus(EnableStatus.Normal.getCode());
        return convert;
    }

    public User updateUserData(){
        Assert.isTrue(U.isNotBlank(id),"用户ID不能为空");
        User convert = JsonUtil.convert(this, User.class);
        convert.setModifyId(WebPlatformSessionUtil.getUserId());
        return convert;
    }
}
