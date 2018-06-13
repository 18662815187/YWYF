package com.lwyykj.core.type.service;

import java.util.List;

import com.lwyykj.core.bean.type.ProType;

import cn.itcast.common.page.Pagination;

public interface TypeService {
	// 列表带分页
	public Pagination selectByPagination(Integer pageNo, String typeName);

	// 查询全部
	List<ProType> queryAll();

	// 根据ID查询
	ProType queryById(Integer id);

	// 新增或更新
	int saveOrUpdate(ProType proType);

	// 根据主键删除
	int delById(Integer id);

	// 批量删除
	int delByIds(Integer[] ids);
	
}
