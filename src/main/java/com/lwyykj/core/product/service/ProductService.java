package com.lwyykj.core.product.service;

import com.lwyykj.core.bean.product.Product;

import cn.itcast.common.page.Pagination;

public interface ProductService {
	// list带分页
	public Pagination selectAllProduct(Integer pageNo, String pro_name,String ph_name, Boolean isShow);

	// 通过主键查询
	Product selectById(Integer id);
	//保存或更新
	int saveOrUpdate(Product product);
	// 批量删除
	int delByIds(Integer[] ids);
	// 批量上架
	int upByIds(Integer[] ids);
	//批量下架
	int unShow(Integer[] ids);
}
