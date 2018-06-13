package com.lwyykj.core.text.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lwyykj.core.bean.text.MailFee;
import com.lwyykj.core.bean.text.MailFeeQuery;
import com.lwyykj.core.bean.text.MailFeeQuery.Criteria;
import com.lwyykj.core.dao.text.MailFeeDao;
import com.lwyykj.core.text.service.MailFeeService;

import cn.itcast.common.page.Pagination;


/**
 * 
 * @author yl 2018.5.8 运费模版ID
 *
 */
@Service("/mailFeeService")
@Transactional
public class MailFeeServiceImpl implements MailFeeService {
	@Resource
	private MailFeeDao mailFeeDao;

	@Override
	public List<MailFee> selectAll(Integer phId) {
		if (null != phId && phId > 0) {
			MailFeeQuery mailFeeQuery = new MailFeeQuery();
			mailFeeQuery.setFields("id,title");
			Criteria createCriteria = mailFeeQuery.createCriteria();
			createCriteria.andIsUseEqualTo(true);
			createCriteria.andPhIdEqualTo(phId);
			return mailFeeDao.selectByExample(mailFeeQuery);
		}else{
			return null;
		}
	}
	@Override
	public Pagination selectByPagination(String keyword, Integer pageNo) {
		StringBuilder params = new StringBuilder();
		MailFeeQuery mailFeeQuery = new MailFeeQuery();
		mailFeeQuery.setPageNo(Pagination.cpn(pageNo));
		mailFeeQuery.setFields("id, ph_id, money, is_use,addtime,title,top_up,money1");
		mailFeeQuery.setPageSize(20);
		Criteria createCriteria = mailFeeQuery.createCriteria();
		if (null != keyword) {
			createCriteria.andTitleLike("%" + keyword + "%");
			params.append("keyword=").append(keyword);
		}
		String url = "AboutUs/list.do";
		Pagination pagination = new Pagination(mailFeeQuery.getPageNo(), mailFeeQuery.getPageSize(),
				mailFeeDao.countByExample(mailFeeQuery), mailFeeDao.selectByExample(mailFeeQuery));
		pagination.pageView(url, params.toString());
		return pagination;
	}
	@Override
	public MailFee selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mailFeeDao.selectByPrimaryKey(id);
	}
	@Override
	public int saveOrUpdate(MailFee mailFee) {
		
			int result = 0;
			if (null != mailFee.getId() && mailFee.getId() > 0) {
				result = mailFeeDao.updateByPrimaryKeySelective(mailFee);
			} else {
				
				result = mailFeeDao.insertSelective(mailFee);
			}
			return result;
		
	}
	@Override
	public int delByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return mailFeeDao.deletes(ids);
	}
}
