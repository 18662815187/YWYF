package com.lwyykj.core.text.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwyykj.core.bean.text.AboutUs;
import com.lwyykj.core.bean.text.AboutUsQuery;
import com.lwyykj.core.bean.text.AboutUsQuery.Criteria;
import com.lwyykj.core.dao.text.AboutUsDao;
import com.lwyykj.core.text.service.AboutUsService;

import cn.itcast.common.page.Pagination;

@Service("aboutUsService")
public class AboutUsServiceImpl implements AboutUsService {
	@Resource
	private AboutUsDao aboutUsDao;

	@Override
	public Pagination selectByPagination(String keyword, Integer pageNo) {
		StringBuilder params = new StringBuilder();
		AboutUsQuery aboutUsQuery = new AboutUsQuery();
		aboutUsQuery.setPageNo(Pagination.cpn(pageNo));
		aboutUsQuery.setFields("id, title, content, type");
		aboutUsQuery.setPageSize(20);
		Criteria createCriteria = aboutUsQuery.createCriteria();
		if (null != keyword) {
			createCriteria.andTitleLike("%" + keyword + "%");
			params.append("keyword=").append(keyword);
		}
		String url = "AboutUs/list.do";
		Pagination pagination = new Pagination(aboutUsQuery.getPageNo(), aboutUsQuery.getPageSize(),
				aboutUsDao.countByExample(aboutUsQuery), aboutUsDao.selectByExample(aboutUsQuery));
		pagination.pageView(url, params.toString());
		return pagination;
	}

	@Override
	public AboutUs selectByPrimaryKey(Integer id) {
		AboutUs aboutUs = aboutUsDao.selectByPrimaryKey(id);
		return aboutUs;
	}

	@Override
	public int apply(AboutUs aboutUs) {
		// TODO Auto-generated method stub
		int result = 0;
		if (null != aboutUs.getId() && aboutUs.getId() > 0) {
			result = aboutUsDao.updateByPrimaryKeySelective(aboutUs);
		} else {
			result = aboutUsDao.insertSelective(aboutUs);
		}
		return result;
	}

	@Override
	public int delByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return aboutUsDao.deletes(ids);
	}

	@Override
	public int show(Integer[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int unShow(Integer[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

}
