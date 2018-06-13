package com.lwyykj.core.dao.text;

import com.lwyykj.core.bean.text.Links;
import com.lwyykj.core.bean.text.LinksQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LinksDao {
    int countByExample(LinksQuery example);

    int deleteByExample(LinksQuery example);

    int deleteByPrimaryKey(Integer id);
    int deletes(Integer[] ids);
    int insert(Links record);

    int insertSelective(Links record);

    List<Links> selectByExample(LinksQuery example);

    Links selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Links record, @Param("example") LinksQuery example);

    int updateByExample(@Param("record") Links record, @Param("example") LinksQuery example);

    int updateByPrimaryKeySelective(Links record);

    int updateByPrimaryKey(Links record);
    int showAll(Integer[] ids);
    int unShowAll(Integer[] ids);
}