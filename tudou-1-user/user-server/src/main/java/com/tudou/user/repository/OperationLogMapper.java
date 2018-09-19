package com.tudou.user.repository;

import com.github.liuanxin.page.model.PageBounds;
import com.tudou.user.model.OperationLog;
import com.tudou.user.model.OperationLogExample;
import com.tudou.user.model.OperationLogWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperationLogMapper {
    int countByExample(OperationLogExample example);

    int deleteByExample(OperationLogExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(OperationLogWithBLOBs record);

    List<OperationLogWithBLOBs> selectByExampleWithBLOBs(OperationLogExample example, PageBounds page);

    List<OperationLogWithBLOBs> selectByExampleWithBLOBs(OperationLogExample example);

    List<OperationLog> selectByExample(OperationLogExample example, PageBounds page);

    List<OperationLog> selectByExample(OperationLogExample example);

    OperationLogWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OperationLogWithBLOBs record, @Param("example") OperationLogExample example);

    int updateByPrimaryKeySelective(OperationLogWithBLOBs record);
}