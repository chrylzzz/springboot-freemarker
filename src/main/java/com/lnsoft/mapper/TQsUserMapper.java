package com.lnsoft.mapper;

import com.lnsoft.po.TQsUser;
import com.lnsoft.po.TQsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TQsUserMapper {
    int countByExample(TQsUserExample example);

    int deleteByExample(TQsUserExample example);

    int deleteByPrimaryKey(Integer objId);

    int insert(TQsUser record);

    int insertSelective(TQsUser record);

    List<TQsUser> selectByExample(TQsUserExample example);

    TQsUser selectByPrimaryKey(Integer objId);

    int updateByExampleSelective(@Param("record") TQsUser record, @Param("example") TQsUserExample example);

    int updateByExample(@Param("record") TQsUser record, @Param("example") TQsUserExample example);

    int updateByPrimaryKeySelective(TQsUser record);

    int updateByPrimaryKey(TQsUser record);
}