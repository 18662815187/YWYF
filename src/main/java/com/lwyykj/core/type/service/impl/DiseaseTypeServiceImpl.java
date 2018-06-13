package com.lwyykj.core.type.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lwyykj.core.bean.type.Disease;
import com.lwyykj.core.bean.type.DiseaseQuery;
import com.lwyykj.core.bean.type.DiseaseQuery.Criteria;
import com.lwyykj.core.dao.type.DiseaseDao;
import com.lwyykj.core.dao.type.FunTypeDao;
import com.lwyykj.core.type.service.DiseaseTypeService;
import cn.itcast.common.page.Pagination;

@Service("diseaseTypeService")
@Transactional
public class DiseaseTypeServiceImpl implements DiseaseTypeService {
	@Resource
	private DiseaseDao diseaseDao;
	@Resource
	private FunTypeDao funTypeDao;

	// 列表带分页
	@Override
	public Pagination selectByPagination(Integer pageNo, String dis_name, Integer funTypeId) {
		StringBuilder params = new StringBuilder();
		DiseaseQuery diseaseQuery = new DiseaseQuery();
		diseaseQuery.setOrderByClause("id desc");
		diseaseQuery.setPageNo(Pagination.cpn(pageNo));
		diseaseQuery.setPageSize(20);
		Criteria createCriteria = diseaseQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		if (null != dis_name) {
			createCriteria.andNameLike("%" + dis_name + "%");
			params.append("dis_name=").append(dis_name);

		}
		if (null != funTypeId) {
			createCriteria.andFuncIdEqualTo(funTypeId);
			params.append("&funTypeId=").append(funTypeId);
		}
		List<Disease> diseases = diseaseDao.selectByExample(diseaseQuery);
		for (Disease disease : diseases) {
			disease.setPar_name(funTypeDao.selectByPrimaryKey(disease.getFuncId()).getName());
		}
		String url = "DisType/list.do";
		Pagination pagination = new Pagination(diseaseQuery.getPageNo(), diseaseQuery.getPageSize(),
				diseaseDao.countByExample(diseaseQuery), diseases);
		pagination.pageView(url, params.toString());
		return pagination;
	}

	// 查询所有用于页面checkBox选择
	@Override
	public List<Disease> queryAll() {
		DiseaseQuery diseaseQuery = new DiseaseQuery();
		diseaseQuery.setOrderByClause("id desc");
		diseaseQuery.setFields("id,name");
		return diseaseDao.selectByExample(diseaseQuery);
	}

	// 根据ID查询
	@Override
	public Disease findById(Integer id) {
		return diseaseDao.selectByPrimaryKey(id);
	}

	@Override
	public int delById(Integer id) {
		return diseaseDao.delById(id);
	}

	// 保存或更新
	@Override
	public int saveOrUpdate(Disease disease) {
		if (null != disease.getId() && disease.getId() > 0) {
			return diseaseDao.updateByPrimaryKeySelective(disease);
		}
		disease.setIsDel(false);
		return diseaseDao.insertSelective(disease);
	}

	@Override
	public int delByIds(Integer[] ids) {
		return diseaseDao.delByIds(ids);
	}

	@Override
	public List<Disease> findByFid(Integer fid) {
		DiseaseQuery diseaseQuery = new DiseaseQuery();
		Criteria createCriteria = diseaseQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		createCriteria.andFuncIdEqualTo(fid);
		return diseaseDao.selectByExample(diseaseQuery);
	}

}
