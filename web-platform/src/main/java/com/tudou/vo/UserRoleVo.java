package com.tudou.vo;

import com.github.liuanxin.api.annotation.ApiReturn;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tudou.base.model.Role;
import com.tudou.base.model.UserRole;
import com.tudou.common.util.A;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true)
public class UserRoleVo {

    @ApiReturn("角色ID")
    private Long value;

    @ApiReturn("名称")
    private String name;

    @ApiReturn("是否选中")
    private Boolean selected = Boolean.FALSE;

    public static List<UserRoleVo> assemblyData(List<UserRole> list, List<Role> all) {
        if(A.isNotEmpty(all)){
            Set<Long> userRoleSet = Sets.newHashSet();
            if (A.isNotEmpty(list)) {
                for(UserRole userRole:list){
                    userRoleSet.add(userRole.getRoleId());
                }
            }
            List<UserRoleVo> voList = Lists.newArrayList();
            for (Role role : all) {
                UserRoleVo vo = new UserRoleVo()
                        .setValue(role.getId())
                        .setName(role.getTitle());
                if(userRoleSet.contains(role.getId())){
                    vo.setSelected(Boolean.TRUE);
                }
                voList.add(vo);
            }
            return voList;
        }
        return null;
    }
}
