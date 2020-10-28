package com.tudou.dto;

import com.github.liuanxin.api.annotation.ApiParam;
import com.tudou.base.model.Role;
import com.tudou.common.json.JsonUtil;
import com.tudou.common.util.U;
import com.tudou.util.WebPlatformSessionUtil;
import lombok.Data;
import org.springframework.util.Assert;

@Data
public class RoleDto {

    @ApiParam("编号")
    private Long id;

    @ApiParam("角色名称")
    private String name;

    @ApiParam("角色标题")
    private String title;

    @ApiParam("角色描述")
    private String description;

    @ApiParam("排序")
    private Long orders;

    public Role addRoleData(){
        Role convert = JsonUtil.convert(this, Role.class);
        Long userId = WebPlatformSessionUtil.getUserId();
        convert.setCreateId(userId)
                .setModifyId(userId);
        return convert;
    }

    public Role updateRoleData(){
        Assert.isTrue(U.isNotBlank(id),"角色ID不能为空");
        Role convert = JsonUtil.convert(this, Role.class);
        convert.setModifyId(WebPlatformSessionUtil.getUserId());
        return convert;
    }
}
