package com.lwyykj.core.dao.text;

import com.lwyykj.core.bean.text.ProInfo;
import com.lwyykj.core.bean.text.ProInfoQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProInfoDao {
    int countByExample(ProInfoQuery example);

    int deleteByExample(ProInfoQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProInfo record);

    int insertSelective(ProInfo record);

    List<ProInfo> selectByExample(ProInfoQuery example);

    ProInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProInfo record, @Param("example") ProInfoQuery example);

    int updateByExample(@Param("record") ProInfo record, @Param("example") ProInfoQuery example);

    int updateByPrimaryKeySelective(ProInfo record);

    int updateByPrimaryKey(ProInfo record);
}