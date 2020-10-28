package com.tudou.dto;

import com.github.liuanxin.api.annotation.ApiParam;
import com.tudou.base.model.Permission;
import com.tudou.common.json.JsonUtil;
import lombok.Data;

@Data
public class PermissionListDto {

    @ApiParam("名称")
    private String name;

    public Permission permissionData(){
        return JsonUtil.convert(this,Permission.class);
    }
}
