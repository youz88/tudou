package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;

import java.util.List;

import com.tudou.user.model.BasePermission;
import com.tudou.user.model.BasePermissionExample;
import org.apache.ibatis.annotations.Param;

public interface BasePermissionMapper {
    int countByExample(BasePermissionExample example);

    int deleteByExample(BasePermissionExample example);

    int deleteByPrimaryKey(Long permissionId);

    int insertSelective(BasePermission record);

    List<BasePermission> selectByExample(BasePermissionExample example, PageBounds page);

    List<BasePermission> selectByExample(BasePermissionExample example);

    BasePermission selectByPrimaryKey(Long permissionId);

    int updateByExampleSelective(@Param("record") BasePermission record, @Param("example") BasePermissionExample example);

    int updateByPrimaryKeySelective(BasePermission record);

    List<BasePermission> selectPermissionByUser(@Param("userId") Long userId);

}