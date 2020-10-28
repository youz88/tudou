package com.tudou.vo;

import com.github.liuanxin.api.annotation.ApiReturn;
import com.tudou.base.model.Permission;
import com.tudou.common.PageInfo;
import com.tudou.common.json.JsonUtil;
import lombok.Data;

import java.util.List;

@Data
public class PermissionVo {

    @ApiReturn("ID")
    private Long id;

    @ApiReturn("所属上级")
    private Long pid;

    @ApiReturn("名称")
    private String name;

    @ApiReturn("类型(1:目录,2:菜单,3:按钮)")
    private Integer type;

    @ApiReturn("权限方法(get,head,post,put,delete)")
    private String method;

    @ApiReturn("路径")
    private String url;

    @ApiReturn("图标")
    private String icon;

    @ApiReturn("状态(0:禁用,1:正常)")
    private Integer status;

    @ApiReturn("排序")
    private Long orders;

    public static PageInfo<PermissionVo> assemblyData(PageInfo pageInfo) {
        pageInfo.setList(JsonUtil.convertList(pageInfo.getList(), PermissionVo.class));
        return pageInfo;
    }

    public static List<PermissionVo> assemblyData(List<Permission> list) {
        return JsonUtil.convertList(list, PermissionVo.class);
    }

    public static PermissionVo assemblyData(Permission permission) {
        return JsonUtil.convert(permission, PermissionVo.class);
    }
}
