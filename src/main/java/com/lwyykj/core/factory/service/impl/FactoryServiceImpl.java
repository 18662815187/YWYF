package com.lwyykj.core.factory.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lwyykj.common.EncodePassword;
import com.lwyykj.core.bean.factory.Factory;
import com.lwyykj.core.bean.factory.FactoryQuery;
import com.lwyykj.core.bean.factory.FactoryQuery.Criteria;
import com.lwyykj.core.dao.factory.FactoryDao;
import com.lwyykj.core.factory.service.FactoryService;

import cn.itcast.common.page.Pagination;

@Service("factoryService")
@Transactional
public class FactoryServiceImpl implements FactoryService {
	@Resource
	private FactoryDao factoryDao;

	// 列表带分页
	@Override
	public Pagination selectByPagination(Integer pageNo, String fac_name, Boolean isShow, String provincesId,
			String cityId, String area) {
		FactoryQuery factoryQuery = new FactoryQuery();
		Criteria createCriteria = factoryQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		factoryQuery.setOrderByClause("id desc");
		factoryQuery.setPageNo(Pagination.cpn(pageNo));
		factoryQuery.setPageSize(20);
		StringBuilder params = new StringBuilder();
		if (null != fac_name) {
			createCriteria.andNameLike("%" + fac_name + "%");
			params.append("fac_name=").append(fac_name);
		}
		if (null != isShow) {
			createCriteria.andIsShowEqualTo(isShow);
			params.append("&isShow=").append(isShow);
		}
		if (null != provincesId) {
			createCriteria.andProvincesIdEqualTo(provincesId);
			params.append("&provincesId=").append(provincesId);
		}
		if (null != cityId) {
			createCriteria.andCityIdEqualTo(cityId);
			params.append("&cityId=").append(cityId);
		}
		if (null != area) {
			createCriteria.andAreaEqualTo(area);
			params.append("&area=").append(area);
		}
		Pagination pagination = new Pagination(factoryQuery.getPageNo(), factoryQuery.getPageSize(),
				factoryDao.countByExample(factoryQuery), factoryDao.selectByExample(factoryQuery));
		String url = "factory/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}

	// 查询所有厂家
	@Override
	public List<Factory> findAll() {
		FactoryQuery factoryQuery = new FactoryQuery();
		factoryQuery.setFields("name,id");
		Criteria createCriteria = factoryQuery.createCriteria();
		createCriteria.andIsShowEqualTo(true);
		createCriteria.andIsDelEqualTo(false);
		return factoryDao.selectByExample(factoryQuery);
	}

	// 保存或更新
	@Override
	public int saveOrUpdate(Factory factory, Integer type) {
		if (factory.getId() != null && factory.getId() > 0) {
			Factory factory2 = factoryDao.selectByPrimaryKey(factory.getId());
			if (factory.getPass().equals(factory2.getPass())) {
				return factoryDao.updateByPrimaryKeySelective(factory);
			} else {
				factory.setPass(EncodePassword.encodePassword(factory.getPass()));
				return factoryDao.updateByPrimaryKeySelective(factory);
			}
		}
		factory.setPass(EncodePassword.encodePassword(factory.getPass()));
		factory.setIsDel(false);
		factory.setRegTime((int) (new Date().getTime() / 1000));
		factory.setRegType(type);
		return factoryDao.insertSelective(factory);
	}

	@Override
	public int delById(Integer id) {
		return factoryDao.delById(id);
	}

	@Override
	public int delByIds(Integer[] ids) {
		return factoryDao.deletes(ids);
	}

	@Override
	public Factory findById(Integer id) {
		return factoryDao.selectByPrimaryKey(id);
	}

	@Override
	public int upByIDS(Integer[] ids) {

		return factoryDao.upByIds(ids);
	}

}
