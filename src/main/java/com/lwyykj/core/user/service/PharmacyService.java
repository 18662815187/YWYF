package com.lwyykj.core.user.service;

import java.util.List;

import com.lwyykj.core.bean.pharmacy.Pharmacy;

import cn.itcast.common.page.Pagination;

public interface PharmacyService {
	// 列表
	public Pagination selectByPagination(Integer pageNo, String hosName,String phName, String provincesId, String cityId,String area,Boolean isShow);

	// 根据主键查询
	Pharmacy findById(Integer id);

	// 新增或更新
	int saveOrUpdate(Pharmacy pharmacy, Integer editType);

	// 批量删除
	int delByIds(Integer[] ids);

	// 批量审核
	int upByIds(Integer[] ids);
	//查询所有
	List<Pharmacy> findAll();
}
