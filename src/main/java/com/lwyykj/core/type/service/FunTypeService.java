package com.lwyykj.core.type.service;

import java.util.List;

import com.lwyykj.core.bean.type.FunType;

import cn.itcast.common.page.Pagination;

public interface FunTypeService {
	// 列表
	public Pagination selectByPagination(Integer pageNo, String FtName, Integer par_id);

	// 保存或更新
	int saveOrUpdate(FunType funType);

	// 根据ID查询
	FunType findById(Integer id);

	// 根据ID删除
	int delById(Integer id);

	// 批量删除
	int delByIds(Integer[] ids);

	// 查询所有
	List<FunType> findAll();

	// 根据上级ID查询
	List<FunType> findByParId(Integer parId);

	// 根据ID把父级ID更新进去
	int updateByIds(Integer pid, List<FunType> funTypes);

}
