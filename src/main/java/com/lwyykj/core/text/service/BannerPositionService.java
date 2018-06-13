package com.lwyykj.core.text.service;

import java.util.List;

import com.lwyykj.core.bean.text.BannerPosition;

import cn.itcast.common.page.Pagination;

public interface BannerPositionService {
	//列表
	Pagination selectByPagination(Integer pageNo,String name);
	//根据主键查询
	BannerPosition findById(Integer id);
	//批量删除
	int delByIds(Integer[] ids);
	//保存更新
	int saveOrUpdate(BannerPosition bannerPosition);
	//查询所有
	List<BannerPosition> queryAll();
}
