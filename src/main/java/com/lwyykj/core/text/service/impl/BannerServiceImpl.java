package com.lwyykj.core.text.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.common.Constants;
import com.lwyykj.core.bean.text.Banner;
import com.lwyykj.core.bean.text.BannerQuery;
import com.lwyykj.core.bean.text.BannerQuery.Criteria;
import com.lwyykj.core.dao.text.BannerDao;
import com.lwyykj.core.dao.text.BannerPositionDao;
import com.lwyykj.core.text.service.BannerService;

import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.4.4
 *
 */
@Service("bannerService")
@Transactional
public class BannerServiceImpl implements BannerService {
	@Resource
	private BannerDao bannerDao;
	@Resource
	private BannerPositionDao bannerPositionDao;

	// 列表
	@Override
	public Pagination selectByPagination(Integer pageNo, String name, Integer positionId) {
		String rUrl = "";
		StringBuilder params = new StringBuilder();
		BannerQuery bannerQuery = new BannerQuery();
		bannerQuery.setOrderByClause("id desc");
		bannerQuery.setPageNo(Pagination.cpn(pageNo));
		bannerQuery.setPageSize(20);
		Criteria createCriteria = bannerQuery.createCriteria();
		createCriteria.andStatusNotEqualTo(2);
		if (null != name) {
			createCriteria.andNameLike("%" + name + "%");
			params.append("name=").append(name);
		}
		if (null != positionId) {
			createCriteria.andPositionEqualTo(positionId);
			params.append("&positionId=").append(positionId);
		}
		String url = "banner/list.do";
		List<Banner> banners = bannerDao.selectByExample(bannerQuery);
		for (Banner banner : banners) {
			banner.setPosName(bannerPositionDao.selectByPrimaryKey(banner.getPosition()).getPosition());
			switch (banner.getType()) {
			// 产品
			case 1:
				rUrl = Constants.DOMAIN + "/product/query?id=" + banner.getLinkId();
				banner.setUrl(rUrl);
				break;
			// 医生
			case 2:
				rUrl = Constants.DOMAIN + "/doctor/query?id=" + banner.getLinkId();
				banner.setUrl(rUrl);
				break;
			// 活动
			case 3:
				rUrl = Constants.DOMAIN + "/activity/query?id=" + banner.getLinkId();
				banner.setUrl(rUrl);
				break;
			// 通知
			default:
				rUrl = Constants.DOMAIN + "/text/query?id=" + banner.getLinkId();
				banner.setUrl(rUrl);
				break;
			}
		}
		Pagination pagination = new Pagination(bannerQuery.getPageNo(), bannerQuery.getPageSize(),
				bannerDao.countByExample(bannerQuery), banners);
		pagination.pageView(url, params.toString());
		return pagination;
	}

	@Override
	public Banner selectById(Integer id) {
		return bannerDao.selectByPrimaryKey(id);
	}

	@Override
	public int saveOrUpdate(Banner banner) {
		int result = 0;
		if (null != banner.getId()) {
			result = bannerDao.updateByPrimaryKeySelective(banner);
		} else {
			banner.setAddtime((int) (new Date().getTime() / 1000));
			result = bannerDao.insertSelective(banner);
		}
		return result;
	}

	@Override
	public int delByIds(Integer[] ids) {
		return bannerDao.deletes(ids);
	}

	@Override
	public int upByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Banner> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
