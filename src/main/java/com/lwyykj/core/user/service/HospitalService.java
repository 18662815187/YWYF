package com.lwyykj.core.user.service;

import java.util.List;

import com.lwyykj.core.bean.doctor.Hospital;

import cn.itcast.common.page.Pagination;

public interface HospitalService {
	// 列表
	public Pagination selectByPagination(Integer pageNo, String hosName, String provincesId, String cityId,String area,Boolean isShow);

	// 根据主键查询
	Hospital findById(Integer id);

	// 新增或更新
	int saveOrUpdate(Hospital hospital,Integer type);

	// 单删更新删除状态
	int delById(Integer id);
	
	//批量删除
	int delByIds(Integer[] ids);
	
	//已审
	int upById(Integer id);
	//批量审核
	int upByIds(Integer[] ids);
	//查询所有
	List<Hospital> findAll();
}
