package com.lwyykj.core.type.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

/**
 * @author yl  2018.3.9
 */
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.type.FunType;
import com.lwyykj.core.bean.type.FunTypeQuery;
import com.lwyykj.core.bean.type.FunTypeQuery.Criteria;
import com.lwyykj.core.dao.type.FunTypeDao;
import com.lwyykj.core.dao.type.ProTypeDao;
import com.lwyykj.core.type.service.FunTypeService;
import cn.itcast.common.page.Pagination;

@Service("/funTypeService")
@Transactional
public class FunTypeServiceImpl implements FunTypeService {
	@Resource
	private FunTypeDao funTypeDao;
	@Resource
	private ProTypeDao proTypeDao;

	// 列表
	@Override
	public Pagination selectByPagination(Integer pageNo, String FtName, Integer par_id) {
		StringBuilder params = new StringBuilder();
		FunTypeQuery funTypeQuery = new FunTypeQuery();
		funTypeQuery.setPageNo(Pagination.cpn(pageNo));
		funTypeQuery.setOrderByClause("id desc");
		funTypeQuery.setPageSize(20);
		Criteria createCriteria = funTypeQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		if (null != FtName) {
			createCriteria.andNameLike("%" + FtName + "%");
			params.append("FrName=").append(FtName);
		}
		if (null != par_id) {
			createCriteria.andTypeIdEqualTo(par_id);
			params.append("&par_id=").append(par_id);
		}
		List<FunType> funTypes = funTypeDao.selectByExample(funTypeQuery);
		for (FunType funType : funTypes) {
			funType.setParName(proTypeDao.selectByPrimaryKey(funType.getTypeId()).getTypeName());
		}
		Pagination pagination = new Pagination(funTypeQuery.getPageNo(), funTypeQuery.getPageSize(),
				funTypeDao.countByExample(funTypeQuery), funTypes);
		String url = "funType/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}

	// 保存或更新
	@Override
	public int saveOrUpdate(FunType funType) {
		if (null != funType.getId() && funType.getId() > 0) {
			return funTypeDao.updateByPrimaryKeySelective(funType);
		}
		funType.setAddTime((int) (new Date().getTime() / 1000));
		funType.setIsDel(false);
		return funTypeDao.insertSelective(funType);
	}

	@Override
	public FunType findById(Integer id) {
		return funTypeDao.selectByPrimaryKey(id);
	}

	@Override
	public int delById(Integer id) {
		return funTypeDao.delById(id);
	}

	@Override
	public int delByIds(Integer[] ids) {
		return funTypeDao.delByIds(ids);
	}

	@Override
	public List<FunType> findAll() {
		FunTypeQuery funTypeQuery = new FunTypeQuery();
		funTypeQuery.setOrderByClause("id desc");
		funTypeQuery.setFields("id,name");
		Criteria createCriteria = funTypeQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		return funTypeDao.selectByExample(funTypeQuery);
	}

	@Override
	public List<FunType> findByParId(Integer parId) {
		FunTypeQuery funTypeQuery = new FunTypeQuery();
		Criteria createCriteria = funTypeQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		createCriteria.andTypeIdEqualTo(parId);
		return funTypeDao.selectByExample(funTypeQuery);
	}

	@Override
	public int updateByIds(Integer pid, List<FunType> funTypes) {
		
		return funTypeDao.updateByIds(pid, funTypes);
	}

}
