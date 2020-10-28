package com.tudou.dto;

import com.github.liuanxin.api.annotation.ApiParam;
import com.tudou.base.enums.EnableStatus;
import com.tudou.base.model.Permission;
import com.tudou.common.json.JsonUtil;
import com.tudou.common.util.U;
import com.tudou.util.WebPlatformSessionUtil;
import lombok.Data;
import org.springframework.util.Assert;

@Data
public class PermissionDto {

    @ApiParam("编号")
    private Long id;

    @ApiParam("所属上级")
    private Long pid;

    @ApiParam("名称")
    private String name;

    @ApiParam("类型(1:目录,2:菜单,3:按钮)")
    private Integer type;

    @ApiParam("权限方法(get,head,post,put,delete)")
    private String method;

    @ApiParam("路径")
    private String url;

    @ApiParam("图标")
    private String icon;

    @ApiParam("状态(0:禁用,1:正常)")
    private Integer status;

    @ApiParam("排序")
    private Long orders;

    public Permission addPermissionData(){
        Permission convert = JsonUtil.convert(this, Permission.class);
        Long userId = WebPlatformSessionUtil.getUserId();
        convert.setCreateId(userId)
                .setModifyId(userId)
                .setStatus(EnableStatus.Normal.getCode());
        return convert;
    }

    public Permission updatePermissionData(){
        Assert.isTrue(U.isNotBlank(id),"权限ID不能为空");
        Permission convert = JsonUtil.convert(this, Permission.class);
        convert.setModifyId(WebPlatformSessionUtil.getUserId());
        return convert;
    }
}
