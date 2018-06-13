package com.lwyykj.core.gift.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.product.Gift;
//import com.lwyykj.core.bean.product.GiftQuery;
//import com.lwyykj.core.bean.product.GiftQuery.Criteria;
import com.lwyykj.core.gift.service.GiftService;

import cn.itcast.common.page.Pagination;

@Service("giftService")
@Transactional
public class GiftServiceImpl implements GiftService {

	@Override
	public Pagination selectPaginationByQuery(Integer pageNo, String StoreName, Integer tel) {
//		GiftQuery giftQuery = new GiftQuery();
//		Criteria createCriteria = giftQuery.createCriteria();
		if (null != StoreName) {
			
		}
		return null;
	}

	@Override
	public int delById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertGift(Gift gift) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(Gift gift) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

}
