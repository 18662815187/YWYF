package com.lwyykj.core.product.service;

import java.util.List;

import com.lwyykj.core.bean.product.Sku;

public interface SkuService {
	// 根据产品ID查询Sku
	List<Sku> findByPid(Integer pid);

	// 保存
	int insert(Sku sku);

	// 更新
	int update(Sku sku);
	
	//删除
	int delBySkuId(Integer skuId);
	//通过主键查询
	Sku findById(Integer id);
	
}
