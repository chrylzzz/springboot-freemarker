package com.lnsoft.mapper;

import com.lnsoft.po.TQsRz;
import com.lnsoft.po.TQsRzExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TQsRzMapper {
    int countByExample(TQsRzExample example);

    int deleteByExample(TQsRzExample example);

    int deleteByPrimaryKey(Integer objId);

    int insert(TQsRz record);

    int insertSelective(TQsRz record);

    List<TQsRz> selectByExample(TQsRzExample example);

    TQsRz selectByPrimaryKey(Integer objId);

    int updateByExampleSelective(@Param("record") TQsRz record, @Param("example") TQsRzExample example);

    int updateByExample(@Param("record") TQsRz record, @Param("example") TQsRzExample example);

    int updateByPrimaryKeySelective(TQsRz record);

    int updateByPrimaryKey(TQsRz record);
}