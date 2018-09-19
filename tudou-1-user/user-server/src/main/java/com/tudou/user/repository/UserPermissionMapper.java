package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;
import com.tudou.user.model.UserPermission;
import com.tudou.user.model.UserPermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPermissionMapper {
    int countByExample(UserPermissionExample example);

    int deleteByExample(UserPermissionExample example);

    int deleteByPrimaryKey(Long userPermissionId);

    int insertSelective(UserPermission record);

    List<UserPermission> selectByExample(UserPermissionExample example, PageBounds page);

    List<UserPermission> selectByExample(UserPermissionExample example);

    UserPermission selectByPrimaryKey(Long userPermissionId);

    int updateByExampleSelective(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);

    int updateByPrimaryKeySelective(UserPermission record);
}