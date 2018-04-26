package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseUserRoleMapper {
    int countByExample(BaseUserRoleExample example);

    int deleteByExample(BaseUserRoleExample example);

    int deleteByPrimaryKey(Long userRoleId);

    int insertSelective(BaseUserRole record);

    List<BaseUserRole> selectByExample(BaseUserRoleExample example, PageBounds page);

    List<BaseUserRole> selectByExample(BaseUserRoleExample example);

    BaseUserRole selectByPrimaryKey(Long userRoleId);

    int updateByExampleSelective(@Param("record") BaseUserRole record, @Param("example") BaseUserRoleExample example);

    int updateByPrimaryKeySelective(BaseUserRole record);
}