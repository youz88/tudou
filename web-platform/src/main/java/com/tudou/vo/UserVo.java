package com.tudou.vo;

import com.github.liuanxin.api.annotation.ApiReturn;
import com.tudou.base.model.User;
import com.tudou.common.PageInfo;
import com.tudou.common.json.JsonUtil;
import lombok.Data;

@Data
public class UserVo {

    @ApiReturn("id")
    private Long id;

    @ApiReturn("帐号")
    private String username;

    @ApiReturn("姓名")
    private String realname;

    @ApiReturn("头像")
    private String avatar;

    @ApiReturn("电话")
    private String phone;

    @ApiReturn("邮箱")
    private String email;

    @ApiReturn("性别")
    private Integer gender;

    @ApiReturn("状态(0:禁用,1:正常)")
    private Integer status;


    public static UserVo assemblyData(User user){
        return JsonUtil.convert(user, UserVo.class);
    }

    public static PageInfo<UserVo> assemblyData(PageInfo pageInfo) {
        pageInfo.setList(JsonUtil.convertList(pageInfo.getList(), UserVo.class));
        return pageInfo;
    }
}
