package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;
import com.github.liuanxin.page.model.PageList;
import com.tudou.user.model.User;
import com.tudou.user.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    PageList<User> selectByExample(UserExample example, PageBounds page);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);
}