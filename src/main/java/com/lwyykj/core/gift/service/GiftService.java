package com.lwyykj.core.gift.service;

import com.lwyykj.core.bean.product.Gift;

import cn.itcast.common.page.Pagination;

public interface GiftService {
	// 礼物列表
	public Pagination selectPaginationByQuery(Integer pageNo, String StoreName, Integer tel);

	// 删除
	public int delById(Integer id);

	// 新增
	public int insertGift(Gift gift);

	// 修改
	public int updateById(Gift gift);

	// 批量删除
	public int delByIds(Integer[] ids);
}
