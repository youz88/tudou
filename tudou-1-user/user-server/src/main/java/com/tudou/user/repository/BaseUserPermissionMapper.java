package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseUserPermissionMapper {
    int countByExample(BaseUserPermissionExample example);

    int deleteByExample(BaseUserPermissionExample example);

    int deleteByPrimaryKey(Long userPermissionId);

    int insertSelective(BaseUserPermission record);

    List<BaseUserPermission> selectByExample(BaseUserPermissionExample example, PageBounds page);

    List<BaseUserPermission> selectByExample(BaseUserPermissionExample example);

    BaseUserPermission selectByPrimaryKey(Long userPermissionId);

    int updateByExampleSelective(@Param("record") BaseUserPermission record, @Param("example") BaseUserPermissionExample example);

    int updateByPrimaryKeySelective(BaseUserPermission record);
}