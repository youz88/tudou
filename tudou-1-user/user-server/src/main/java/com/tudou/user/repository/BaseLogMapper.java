package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseLogMapper {
    int countByExample(BaseLogExample example);

    int deleteByExample(BaseLogExample example);

    int deleteByPrimaryKey(Long logId);

    int insertSelective(BaseLog record);

    List<BaseLog> selectByExampleWithBLOBs(BaseLogExample example, PageBounds page);

    List<BaseLog> selectByExampleWithBLOBs(BaseLogExample example);

    List<BaseLog> selectByExample(BaseLogExample example, PageBounds page);

    List<BaseLog> selectByExample(BaseLogExample example);

    BaseLog selectByPrimaryKey(Long logId);

    int updateByExampleSelective(@Param("record") BaseLog record, @Param("example") BaseLogExample example);

    int updateByPrimaryKeySelective(BaseLog record);
}