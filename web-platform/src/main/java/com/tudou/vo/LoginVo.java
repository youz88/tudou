package com.tudou.vo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tudou.common.Const;
import com.tudou.common.json.JsonUtil;
import com.tudou.user.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LoginVo {

    @ApiModelProperty("编号")
    private Integer userId;

    @ApiModelProperty("帐号")
    private String username;

    @ApiModelProperty("姓名")
    private String realname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("性别")
    private Byte sex;

    List<Permission> permissions;

    public static LoginVo loginVoData(User user, List<com.tudou.user.model.Permission > permissions) {
        LoginVo convert = JsonUtil.convert(user, LoginVo.class);
        Map<Long,Permission> permissionMap = Maps.newHashMap();
        List<Permission> convertPermissionList = JsonUtil.convertList(permissions, Permission.class);
        convertPermissionList.stream().forEach(permission -> {
            if (Const.DEFAULE_PERMISSION_ID.equals(permission.getPid())) {
                permissionMap.put(permission.getId(),permission);
            } else {
                if(permissionMap.containsKey(permission.getPid())){
                    Permission parent = permissionMap.get(permission.getPid());
                    parent.getChild().add(permission);
                }
            }
        });
        convert.setPermissions(Lists.newArrayList(permissionMap.values()));
        return convert;
    }

    @Data
    static class Permission {

        @ApiModelProperty("ID")
        private Long id;

        @ApiModelProperty("所属上级")
        private Long pid;

        @ApiModelProperty("名称")
        private String name;

        @ApiModelProperty("类型(1:目录,2:菜单,3:按钮)")
        private Byte type;

        @ApiModelProperty("权限方法(get,head,post,put,delete)")
        private String method;

        @ApiModelProperty("路径")
        private String url;

        @ApiModelProperty("图标")
        private String icon;

        @ApiModelProperty("排序")
        private Long orders;

        @ApiModelProperty("子菜单权限")
        List<Permission> child = Lists.newArrayList();

    }
}
