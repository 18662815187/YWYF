package com.lwyykj.core.product.service;

import java.util.List;

import com.lwyykj.core.bean.product.Brand;

import cn.itcast.common.page.Pagination;

public interface BrandService {
	// 查找所有品牌
	List<Brand> findAll();

	// 新增或修改
	int saveOrUpdate(Brand brand);

	// 删除
	int delById(Integer id);

	// 批量删除
	int delByIds(Integer[] ids);

	// 列表带分页
	public Pagination selectByPagination(String Brand_name, Integer pageNo, String fac_name, Boolean isShow);

	// 根据主键查询
	public Brand findById(Integer id);

	// 批量审核通过架
	public int upByIds(Integer[] ids);

}
