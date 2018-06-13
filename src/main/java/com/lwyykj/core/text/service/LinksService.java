package com.lwyykj.core.text.service;

import com.lwyykj.core.bean.text.Links;

import cn.itcast.common.page.Pagination;

public interface LinksService {
	// 列表页
	public Pagination selectByPagination(String keyword, Integer pageNo);

	// 根据主键查找
	Links selectByPrimaryKey(Integer id);

	int insert(Links link);

	int delByIds(Integer[] ids);

	// 保存更新
	int saveOrUpdate(Links link);

	// 批量解禁
	int show(Integer[] ids);

	// 批量禁用
	int unShow(Integer[] ids);

}
