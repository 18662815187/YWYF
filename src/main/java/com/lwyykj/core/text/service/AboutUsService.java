package com.lwyykj.core.text.service;

import com.lwyykj.core.bean.text.AboutUs;

import cn.itcast.common.page.Pagination;

public interface AboutUsService {
	// 列表页
		public Pagination selectByPagination(String keyword, Integer pageNo);
		//根据ID查询
		AboutUs selectByPrimaryKey(Integer id);
		//提交或更新
		int apply(AboutUs aboutUs);
		//批删 该变删除状态
		int delByIds(Integer[] ids);
		//批量显示
		int show(Integer[] ids);
		//批量禁止显示
		int unShow(Integer[] ids);
}
