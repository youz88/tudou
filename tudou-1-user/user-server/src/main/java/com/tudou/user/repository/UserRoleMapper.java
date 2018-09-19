package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;
import com.tudou.user.model.UserRole;
import com.tudou.user.model.UserRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(Long userRoleId);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example, PageBounds page);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(Long userRoleId);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);
}