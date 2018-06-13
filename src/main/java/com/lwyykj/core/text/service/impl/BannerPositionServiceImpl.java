package com.lwyykj.core.text.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lwyykj.core.bean.text.BannerPosition;
import com.lwyykj.core.bean.text.BannerPositionQuery;
import com.lwyykj.core.bean.text.BannerPositionQuery.Criteria;
import com.lwyykj.core.dao.text.BannerPositionDao;
import com.lwyykj.core.text.service.BannerPositionService;
import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.4.4
 *
 */
@Service("bannerPositionService")
@Transactional
public class BannerPositionServiceImpl implements BannerPositionService {
	@Resource
	private BannerPositionDao bannerPositionDao;

	// 列表
	@Override
	public Pagination selectByPagination(Integer pageNo, String name) {
		StringBuilder params = new StringBuilder();
		BannerPositionQuery bannerPositionQuery = new BannerPositionQuery();
		bannerPositionQuery.setPageNo(Pagination.cpn(pageNo));
		bannerPositionQuery.setOrderByClause("id desc");
		bannerPositionQuery.setPageSize(20);
		Criteria createCriteria = bannerPositionQuery.createCriteria();
		if (null != name) {
			createCriteria.andPositionLike("%" + name + "%");
			params.append("name=").append(name);
		}
		String url = "posstion/list.do";
		Pagination pagination = new Pagination(bannerPositionQuery.getPageNo(), bannerPositionQuery.getPageSize(),
				bannerPositionDao.countByExample(bannerPositionQuery),
				bannerPositionDao.selectByExample(bannerPositionQuery));
		pagination.pageView(url, params.toString());
		return pagination;
	}

	@Override
	public BannerPosition findById(Integer id) {
		return bannerPositionDao.selectByPrimaryKey(id);
	}

	@Override
	public int delByIds(Integer[] ids) {
		return bannerPositionDao.deletes(ids);
	}

	// 保存更新
	@Override
	public int saveOrUpdate(BannerPosition bannerPosition) {
		int result = 0;
		if (null != bannerPosition.getId()) {
			result = bannerPositionDao.updateByPrimaryKeySelective(bannerPosition);
		} else {
			result = bannerPositionDao.insertSelective(bannerPosition);
		}
		return result;
	}

	// 查询所有
	@Override
	public List<BannerPosition> queryAll() {
		BannerPositionQuery bannerPositionQuery = new BannerPositionQuery();
		List<BannerPosition> bannerPositions = bannerPositionDao.selectByExample(bannerPositionQuery);
		return bannerPositions;
	}

}
