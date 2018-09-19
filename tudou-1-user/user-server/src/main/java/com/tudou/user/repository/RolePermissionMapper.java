package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;
import com.tudou.user.model.RolePermission;
import com.tudou.user.model.RolePermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {
    int countByExample(RolePermissionExample example);

    int deleteByExample(RolePermissionExample example);

    int deleteByPrimaryKey(Long rolePermissionId);

    int insertSelective(RolePermission record);

    List<RolePermission> selectByExample(RolePermissionExample example, PageBounds page);

    List<RolePermission> selectByExample(RolePermissionExample example);

    RolePermission selectByPrimaryKey(Long rolePermissionId);

    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByPrimaryKeySelective(RolePermission record);
}