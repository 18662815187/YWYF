package com.lwyykj.core.text.service;

import com.lwyykj.core.bean.text.Drugstore;

import cn.itcast.common.page.Pagination;

public interface DrugstoreService {
	// 列表页
		public Pagination selectByPagination(String keyword, Integer pageNo);

		// 根据主键查找
		Drugstore selectByPrimaryKey(Integer id);

		int insert(Drugstore drugstore);

		int delByIds(Integer[] ids);

		// 保存更新
		int saveOrUpdate(Drugstore drugstore);

		// 批量审核通过
		int show(Integer[] ids);

		// 批量审核不通过
		int unShow(Integer[] ids);
}
