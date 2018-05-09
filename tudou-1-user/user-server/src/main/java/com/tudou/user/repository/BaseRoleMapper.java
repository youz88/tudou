package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;

import java.util.List;

import com.tudou.user.model.BaseRole;
import com.tudou.user.model.BaseRoleExample;
import org.apache.ibatis.annotations.Param;

public interface BaseRoleMapper {
    int countByExample(BaseRoleExample example);

    int deleteByExample(BaseRoleExample example);

    int deleteByPrimaryKey(Long roleId);

    int insertSelective(BaseRole record);

    List<BaseRole> selectByExample(BaseRoleExample example, PageBounds page);

    List<BaseRole> selectByExample(BaseRoleExample example);

    BaseRole selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("record") BaseRole record, @Param("example") BaseRoleExample example);

    int updateByPrimaryKeySelective(BaseRole record);
}