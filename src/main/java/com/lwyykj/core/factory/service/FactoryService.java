package com.lwyykj.core.factory.service;

import java.util.List;

import com.lwyykj.core.bean.factory.Factory;

import cn.itcast.common.page.Pagination;

public interface FactoryService {
	// 列表带分页
	public Pagination selectByPagination(Integer pageNo, String fac_name, Boolean isShow, String provincesId,
			String cityId, String area);

	// 全部厂家用于新增和编辑
	List<Factory> findAll();

	// 新增或编辑
	int saveOrUpdate(Factory factory,Integer type);

	// 删除
	int delById(Integer id);

	// 批量删除
	int delByIds(Integer[] ids);

	// 通过主键查询
	Factory findById(Integer id);


	// 批量更新已审核
	int upByIDS(Integer[] ids);

}
