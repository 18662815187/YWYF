package com.lwyykj.core.solr.service;

import java.util.List;

import com.lwyykj.core.bean.product.Product;

public interface SolrService {
	//单存
	int saveSolr(Product product);
	//多对象保存
	int saveSolrS(List<Product> products);
	//单删
	int delSolr(Integer id);
	//批删
	int delSolrs(Integer[] ids);
	//新增并提交
	int applyNew(Integer id);
}
