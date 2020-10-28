package com.tudou.vo;

import com.github.liuanxin.api.annotation.ApiReturn;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tudou.base.model.User;
import com.tudou.common.Const;
import com.tudou.common.json.JsonUtil;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LoginVo {

    @ApiReturn("用户ID")
    private Integer id;

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

    @ApiReturn("权限列表")
    List<Permission> permissions;

    public static LoginVo loginVoData(User user, List<com.tudou.base.model.Permission > permissions) {
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

        @ApiReturn("ID")
        private Long id;

        @ApiReturn("所属上级")
        private Long pid;

        @ApiReturn("名称")
        private String name;

        @ApiReturn("类型(1:目录,2:菜单,3:按钮)")
        private Byte type;

        @ApiReturn("权限方法(get,head,post,put,delete)")
        private String method;

        @ApiReturn("路径")
        private String url;

        @ApiReturn("图标")
        private String icon;

        @ApiReturn("排序")
        private Long orders;

        @ApiReturn("子菜单权限")
        List<Permission> child = Lists.newArrayList();

    }
}
