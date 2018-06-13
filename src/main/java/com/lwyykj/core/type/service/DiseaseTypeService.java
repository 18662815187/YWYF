package com.lwyykj.core.type.service;

import java.util.List;

import com.lwyykj.core.bean.type.Disease;

import cn.itcast.common.page.Pagination;

public interface DiseaseTypeService {
	// 列表带分页
	public Pagination selectByPagination(Integer pageNo, String dis_name, Integer funTypeId);

	// 查询所有
	List<Disease> queryAll();

	// 根据ID查询
	Disease findById(Integer id);

	// 删除
	int delById(Integer id);

	// 更新或保存
	int saveOrUpdate(Disease disease);
	
	//批量删除(假删)
	int delByIds(Integer[] ids);
	//根据funtype查询所有下级
	List<Disease> findByFid(Integer fid);
}
