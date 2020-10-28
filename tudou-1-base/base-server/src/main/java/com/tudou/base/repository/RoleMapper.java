package com.tudou.base.repository;

import com.github.liuanxin.page.model.PageBounds;
import com.tudou.base.model.Role;
import com.tudou.base.model.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example, PageBounds page);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);
}