package com.lwyykj.core.type.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.type.ProType;
import com.lwyykj.core.bean.type.ProTypeQuery;
import com.lwyykj.core.bean.type.ProTypeQuery.Criteria;
import com.lwyykj.core.dao.type.ProTypeDao;
import com.lwyykj.core.type.service.TypeService;

import cn.itcast.common.page.Pagination;

@Service("typeService")
@Transactional
public class TypeServiceImpl implements TypeService {
	@Resource
	private ProTypeDao proTypeDao;

	// 列表
	@Override
	public Pagination selectByPagination(Integer pageNo, String type_Name) {
		ProTypeQuery proTypeQuery = new ProTypeQuery();
		Criteria createCriteria = proTypeQuery.createCriteria();
		StringBuilder params = new StringBuilder();
		proTypeQuery.setOrderByClause("id desc");
		proTypeQuery.setPageNo(Pagination.cpn(pageNo));
		proTypeQuery.setPageSize(20);
		createCriteria.andIsDelEqualTo(false);
		if (null != type_Name) {
			createCriteria.andTypeNameLike("%" + type_Name + "%");
			params.append("typeName=").append(type_Name);
		}
		List<ProType> proTypes = proTypeDao.selectByExample(proTypeQuery);
		for (ProType proType : proTypes) {
			if(proType.getParentId()==0){
				proType.setpTypeName("此为顶级分类");
			}else{
				proType.setpTypeName(proTypeDao.selectByPrimaryKey(proType.getParentId()).getTypeName());
			}
		}
		Pagination pagination = new Pagination(proTypeQuery.getPageNo(), proTypeQuery.getPageSize(),
				proTypeDao.countByExample(proTypeQuery), proTypes);
		String url = "type/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}

	// 查询所有
	@Override
	public List<ProType> queryAll() {
		ProTypeQuery proTypeQuery = new ProTypeQuery();
		Criteria createCriteria = proTypeQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		return proTypeDao.selectByExample(proTypeQuery);
	}

	// 通过主键查询
	@Override
	public ProType queryById(Integer id) {
		return proTypeDao.selectByPrimaryKey(id);
	}

	// 更新或新增
	@Override
	public int saveOrUpdate(ProType proType) {
		if (null != proType.getId() && proType.getId() > 0) {
			
			return proTypeDao.updateByPrimaryKeySelective(proType);
		}
		proType.setIsDel(false);
		proType.setAddTime((int) (new Date().getTime() / 1000));
		return proTypeDao.insertSelective(proType);
	}

	// 删除（假）
	@Override
	public int delById(Integer id) {
		return proTypeDao.delById(id);
	}

	// 批量删除
	@Override
	public int delByIds(Integer[] ids) {
		return proTypeDao.delByIds(ids);
	}

}
