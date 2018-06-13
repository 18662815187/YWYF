package com.lwyykj.core.pca.service;

import java.util.List;

import com.lwyykj.core.bean.pca.Areas;
import com.lwyykj.core.bean.pca.Cities;
import com.lwyykj.core.bean.pca.Provinces;

public interface PcaService {
	// 第一级
	List<Provinces> findAll();

	// 第二级
	List<Cities> findByPid(String pid);

	// 第三级
	List<Areas> findByCid(String cid);
}
