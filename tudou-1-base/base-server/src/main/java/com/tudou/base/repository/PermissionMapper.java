package com.tudou.base.repository;

import com.github.liuanxin.page.model.PageBounds;
import com.tudou.base.model.Permission;
import com.tudou.base.model.PermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    int countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example, PageBounds page);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    // other
    List<Permission> selectByRoleId(@Param("roleId") Long roleId);

}