package com.lwyykj.core.dao.text;

import com.lwyykj.core.bean.text.AboutUs;
import com.lwyykj.core.bean.text.AboutUsQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AboutUsDao {
    int countByExample(AboutUsQuery example);

    int deleteByExample(AboutUsQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(AboutUs record);

    int insertSelective(AboutUs record);

    List<AboutUs> selectByExample(AboutUsQuery example);

    AboutUs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AboutUs record, @Param("example") AboutUsQuery example);

    int updateByExample(@Param("record") AboutUs record, @Param("example") AboutUsQuery example);

    int updateByPrimaryKeySelective(AboutUs record);

    int updateByPrimaryKey(AboutUs record);
    //批删
    int deletes(Integer[] ids);
}