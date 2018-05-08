package com.tudou.vo;

import com.tudou.common.json.JsonUtil;
import com.tudou.user.model.BaseUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserVo {

    @ApiModelProperty("ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    public static UserVo assemblyData(BaseUser baseUser){
        return JsonUtil.convert(baseUser,UserVo.class);
    }
}
