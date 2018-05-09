package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;

import java.util.List;

import com.tudou.user.model.BaseUser;
import com.tudou.user.model.BaseUserExample;
import org.apache.ibatis.annotations.Param;

public interface BaseUserMapper {
    int countByExample(BaseUserExample example);

    int deleteByExample(BaseUserExample example);

    int deleteByPrimaryKey(Long userId);

    int insertSelective(BaseUser record);

    List<BaseUser> selectByExample(BaseUserExample example, PageBounds page);

    List<BaseUser> selectByExample(BaseUserExample example);

    BaseUser selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") BaseUser record, @Param("example") BaseUserExample example);

    int updateByPrimaryKeySelective(BaseUser record);
}