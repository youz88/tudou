package com.tudou.vo;

import com.github.liuanxin.api.annotation.ApiReturn;
import com.google.common.collect.Sets;
import com.tudou.base.model.Permission;
import com.tudou.common.json.JsonUtil;
import com.tudou.common.util.A;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PermissionTreeVo {

    @ApiReturn("ID")
    private Long id;

    @ApiReturn("所属上级")
    private Long pid;

    @ApiReturn("名称")
    private String name;

    @ApiReturn("是否选中")
    private Boolean checked = Boolean.FALSE;

    @ApiReturn("是否展开")
    private Boolean open = Boolean.FALSE;

    public static List<PermissionTreeVo> assemblyData(List<Permission> list, List<Permission> all) {
        if(A.isNotEmpty(all)){
            Set<Long> rolePermissionSet = Sets.newHashSet();
            if (A.isNotEmpty(list)) {
                for(Permission permission:list){
                    rolePermissionSet.add(permission.getId());
                }
            }
            List<PermissionTreeVo> permissionTreeVos = JsonUtil.convertList(all, PermissionTreeVo.class);
            for(PermissionTreeVo permission:permissionTreeVos){
                if(rolePermissionSet.contains(permission.getId())){
                    permission.setChecked(Boolean.TRUE);
                    permission.setOpen(Boolean.TRUE);
                }
            }
            return permissionTreeVos;
        }
        return null;
    }
}
