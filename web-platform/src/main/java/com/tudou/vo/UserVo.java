package com.tudou.vo;

import com.tudou.common.json.JsonUtil;
import com.tudou.user.model.BasePermission;
import com.tudou.user.model.BaseUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @ApiModelProperty("权限菜单")
    private List<Menu> menuList;

    public static UserVo assemblyData(BaseUser baseUser, List<BasePermission> basePermissionList){
        UserVo convert = JsonUtil.convert(baseUser, UserVo.class);
        convert.setMenuList(JsonUtil.convertList(basePermissionList,Menu.class));
        return convert;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    static class Menu{

        private Long id;

        /** 所属上级 --> pid */
        private Long pid;

        /** 名称 --> name */
        private String name;

        /** 类型(1:目录,2:菜单,3:按钮) --> type */
        private Integer type;

        /** 权限值 --> permission_value */
        private String permissionValue;

        /** 路径 --> uri */
        private String uri;

        /** 图标 --> icon */
        private String icon;

        /** 状态(0:禁止,1:正常) --> enable */
        private Boolean enable;

        /** 排序 --> orders */
        private Integer orders;

    }
}
