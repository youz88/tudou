package com.tudou.dto;

import com.github.liuanxin.api.annotation.ApiParam;
import com.tudou.base.enums.EnableStatus;
import com.tudou.base.model.User;
import com.tudou.common.Const;
import com.tudou.common.json.JsonUtil;
import com.tudou.common.util.U;
import lombok.Data;
import org.springframework.util.Assert;

@Data
public class RegisteDto {

    @ApiParam("帐号")
    private String username;

    @ApiParam("密码")
    private String password;

    @ApiParam("昵称")
    private String nickname;

    public User userData() {
        Assert.isTrue(U.isNotBlank(username),"用户名不能为空");
        Assert.isTrue(U.isNotBlank(password),"密码不能为空");
        if (U.isBlank(nickname)) {
            nickname = username;
        }
        User convert = JsonUtil.convert(this, User.class);
        convert.setCreateId(Const.DEFAULE_CREATE_ID)
                .setModifyId(Const.DEFAULE_CREATE_ID)
                .setStatus(EnableStatus.Normal.getCode());
        return convert;
    }
}
