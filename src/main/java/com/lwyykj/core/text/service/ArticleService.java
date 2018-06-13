package com.lwyykj.core.text.service;

import com.lwyykj.core.bean.text.Article;

import cn.itcast.common.page.Pagination;

public interface ArticleService {
	// 列表页
	public Pagination selectByPagination(String keyword, Integer pageNo);
	//根据ID查询
	Article selectByPrimaryKey(Integer id);
	//提交或更新
	int apply(Article article);
	//批删 该变删除状态
	int delByIds(Integer[] ids);
	//批量显示
	int show(Integer[] ids);
	//批量禁止显示
	int unShow(Integer[] ids);
}
