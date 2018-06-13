package com.lwyykj.core.dao.text;

import com.lwyykj.core.bean.text.Drugstore;
import com.lwyykj.core.bean.text.DrugstoreQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrugstoreDao {
    int countByExample(DrugstoreQuery example);

    int deleteByExample(DrugstoreQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Drugstore record);

    int insertSelective(Drugstore record);

    List<Drugstore> selectByExample(DrugstoreQuery example);

    Drugstore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Drugstore record, @Param("example") DrugstoreQuery example);

    int updateByExample(@Param("record") Drugstore record, @Param("example") DrugstoreQuery example);

    int updateByPrimaryKeySelective(Drugstore record);

    int updateByPrimaryKey(Drugstore record);
    int deletes(Integer[] ids);
    int showAll(Integer[] ids);
    int unshowAll(Integer[] ids);
    
}