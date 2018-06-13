package com.lwyykj.core.text.service;

import java.util.List;

import com.lwyykj.core.bean.text.Banner;

import cn.itcast.common.page.Pagination;

public interface BannerService {

	// 列表
	Pagination selectByPagination(Integer pageNo, String name, Integer positionId);

	// 根据ID查询
	Banner selectById(Integer id);

	// 保存更新
	int saveOrUpdate(Banner banner);

	// 批量删除
	int delByIds(Integer[] ids);

	// 批量更新
	int upByIds(Integer[] ids);
	
	//查询所有
	List<Banner> queryAll();
}
