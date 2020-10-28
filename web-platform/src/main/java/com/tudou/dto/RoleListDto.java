package com.tudou.dto;

import com.github.liuanxin.api.annotation.ApiParam;
import com.tudou.base.model.Role;
import com.tudou.common.json.JsonUtil;
import lombok.Data;

@Data
public class RoleListDto {

    @ApiParam("角色名称")
    private String name;

    public Role roleData(){
        return JsonUtil.convert(this, Role.class);
    }
}
