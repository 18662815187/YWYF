package com.lwyykj.core.text.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwyykj.core.bean.text.Drugstore;
import com.lwyykj.core.bean.text.DrugstoreQuery;
import com.lwyykj.core.bean.text.DrugstoreQuery.Criteria;
import com.lwyykj.core.dao.text.DrugstoreDao;
import com.lwyykj.core.text.service.DrugstoreService;

import cn.itcast.common.page.Pagination;

@Service("drugstoreService")
public class DrugstoreServiceImpl implements DrugstoreService {
	@Resource
	private DrugstoreDao drugstoreDao;

	@Override
	public Pagination selectByPagination(String keyword, Integer pageNo) {
		StringBuilder params = new StringBuilder();
		DrugstoreQuery drugstoreQuery = new DrugstoreQuery();
		drugstoreQuery.setPageNo(Pagination.cpn(pageNo));
		drugstoreQuery.setFields("id, name, phone, pharmacy,password,status,addtime");
		drugstoreQuery.setPageSize(20);
		Criteria createCriteria = drugstoreQuery.createCriteria();
		createCriteria.andStatusEqualTo(false);
		if (null != keyword) {
			createCriteria.andPharmacyLike("%" + keyword + "%");
			params.append("keyword=").append(keyword);
		}
		String url = "DrugStore/list.do";
		Pagination pagination = new Pagination(drugstoreQuery.getPageNo(), drugstoreQuery.getPageSize(),
				drugstoreDao.countByExample(drugstoreQuery), drugstoreDao.selectByExample(drugstoreQuery));
		pagination.pageView(url, params.toString());
		return pagination;
	}

	@Override
	public Drugstore selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		Drugstore drugstore = drugstoreDao.selectByPrimaryKey(id);
		return drugstore;
	}

	@Override
	public int insert(Drugstore drugstore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return drugstoreDao.deletes(ids);
	}

	@Override
	public int saveOrUpdate(Drugstore drugstore) {
		// TODO Auto-generated method stub
		int result = 0;
		if (null != drugstore.getId()) {
			result = drugstoreDao.updateByPrimaryKeySelective(drugstore);
		} else {
			drugstore.setAddtime(new Date());
			drugstore.setStatus(false);
			result = drugstoreDao.insertSelective(drugstore);
		}
		return result;
	}

	@Override
	public int show(Integer[] ids) {
		// TODO Auto-generated method stub
		return drugstoreDao.showAll(ids);
	}

	@Override
	public int unShow(Integer[] ids) {
		// TODO Auto-generated method stub
		return drugstoreDao.unshowAll(ids);
	}

}
