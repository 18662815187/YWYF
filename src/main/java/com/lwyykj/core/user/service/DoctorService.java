package com.lwyykj.core.user.service;

import java.util.List;

import com.lwyykj.core.bean.doctor.Doctor;

import cn.itcast.common.page.Pagination;

public interface DoctorService {
	// 列表
	public Pagination selectByPagination(Integer pageNo, String docName, String hosName, String provincesId,
			String cityId, String area, Boolean isShow);

	// 根据ID查询
	Doctor findById(Integer id);

	// 保存或更新
	int saveOrUpdate(Doctor doctor,Integer editType);

	// 批量删除
	int delByIds(Integer[] ids);

	// 批量更新
	int upByIds(Integer[] ids);
	//查询所有结果
	List<Doctor> findAll();
}
