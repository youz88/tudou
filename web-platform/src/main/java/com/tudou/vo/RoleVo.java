package com.tudou.vo;

import com.github.liuanxin.api.annotation.ApiReturn;
import com.tudou.base.model.Role;
import com.tudou.common.PageInfo;
import com.tudou.common.json.JsonUtil;
import lombok.Data;

@Data
public class RoleVo {

    @ApiReturn("ID")
    private Long id;

    @ApiReturn("角色名称")
    private String name;

    @ApiReturn("角色标题")
    private String title;

    @ApiReturn("角色描述")
    private String description;

    @ApiReturn("排序")
    private Long orders;

    public static RoleVo assemblyData(Role role) {
        return JsonUtil.convert(role, RoleVo.class);
    }

    public static PageInfo<RoleVo> assemblyData(PageInfo pageInfo) {
        pageInfo.setList(JsonUtil.convertList(pageInfo.getList(), RoleVo.class));
        return pageInfo;
    }
}
