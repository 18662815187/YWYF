package com.lwyykj.core.text.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lwyykj.core.bean.text.Links;
import com.lwyykj.core.bean.text.LinksQuery;
import com.lwyykj.core.bean.text.LinksQuery.Criteria;
import com.lwyykj.core.dao.text.LinksDao;
import com.lwyykj.core.text.service.LinksService;

import cn.itcast.common.page.Pagination;
@Service("/linksService")
@Transactional
public class LinksServiceImpl implements LinksService{

	@Resource
	private LinksDao linksdao;
	@Override
	public Pagination selectByPagination(String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		StringBuilder params = new StringBuilder();
		LinksQuery linksQuery = new LinksQuery();
		linksQuery.setPageNo(Pagination.cpn(pageNo));
		linksQuery.setFields("id, link_url, link_status, listorder,link_name");
		linksQuery.setPageSize(20);
		Criteria createCriteria = linksQuery.createCriteria();
		createCriteria.andLinkStatusNotEqualTo(2);
		if (null != keyword) {
			createCriteria.andLinkNameLike("%" + keyword + "%");
			params.append("keyword=").append(keyword);
		}
		String url = "Links/list.do";
		Pagination pagination = new Pagination(linksQuery.getPageNo(), linksQuery.getPageSize(),
				linksdao.countByExample(linksQuery), linksdao.selectByExample(linksQuery));
		pagination.pageView(url, params.toString());
		return pagination;
	}

	@Override
	public Links selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		Links links = linksdao.selectByPrimaryKey(id);
		return links;
	}

	@Override
	public int insert(Links link) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return linksdao.deletes(ids);
	}

	@Override
	public int saveOrUpdate(Links link) {
		int result = 0;
		if (null != link.getId() && link.getId() > 0) {
			result = linksdao.updateByPrimaryKeySelective(link);
		} else {
			
			result = linksdao.insertSelective(link);
		}
		return result;
	}
	//批量解禁
		@Override
		public int show(Integer[] ids) {
			return linksdao.showAll(ids);
		}
		//批量禁用
		@Override
		public int unShow(Integer[] ids) {
			return linksdao.unShowAll(ids);
		}

}
