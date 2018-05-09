package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;

import java.util.List;

import com.tudou.user.model.BaseRolePermission;
import com.tudou.user.model.BaseRolePermissionExample;
import org.apache.ibatis.annotations.Param;

public interface BaseRolePermissionMapper {
    int countByExample(BaseRolePermissionExample example);

    int deleteByExample(BaseRolePermissionExample example);

    int deleteByPrimaryKey(Long rolePermissionId);

    int insertSelective(BaseRolePermission record);

    List<BaseRolePermission> selectByExample(BaseRolePermissionExample example, PageBounds page);

    List<BaseRolePermission> selectByExample(BaseRolePermissionExample example);

    BaseRolePermission selectByPrimaryKey(Long rolePermissionId);

    int updateByExampleSelective(@Param("record") BaseRolePermission record, @Param("example") BaseRolePermissionExample example);

    int updateByPrimaryKeySelective(BaseRolePermission record);
}