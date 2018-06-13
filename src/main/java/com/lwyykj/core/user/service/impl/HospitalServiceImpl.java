package com.lwyykj.core.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.common.EncodePassword;
import com.lwyykj.core.bean.doctor.Hospital;
import com.lwyykj.core.bean.doctor.HospitalQuery;
import com.lwyykj.core.bean.doctor.HospitalQuery.Criteria;
import com.lwyykj.core.dao.doctor.HospitalDao;
import com.lwyykj.core.user.service.HospitalService;
import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.3.14
 *
 */
@Service("/hospitalService")
@Transactional
public class HospitalServiceImpl implements HospitalService {
	@Resource
	private HospitalDao hospitalDao;

	// 列表
	@Override
	public Pagination selectByPagination(Integer pageNo, String hosName, String provincesId, String cityId, String area,
			Boolean isShow) {
		StringBuilder params = new StringBuilder();
		HospitalQuery hospitalQuery = new HospitalQuery();
		hospitalQuery.setPageSize(20);
		hospitalQuery.setPageNo(Pagination.cpn(pageNo));
		hospitalQuery.setOrderByClause("id desc");
		Criteria createCriteria = hospitalQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		if (null != hosName) {
			createCriteria.andHosNameLike("%" + hosName + "%");
			params.append("hosName=").append(hosName);
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
		if (null != isShow) {
			createCriteria.andIsShowEqualTo(isShow);
			params.append("&isShow=").append(isShow);
		}
		String url = "hospital/list.do";
		Pagination pagination = new Pagination(hospitalQuery.getPageNo(), hospitalQuery.getPageSize(),
				hospitalDao.countByExample(hospitalQuery), hospitalDao.selectByExample(hospitalQuery));
		pagination.pageView(url, params.toString());
		return pagination;
	}

	// 根据主键查询
	@Override
	public Hospital findById(Integer id) {
		return hospitalDao.selectByPrimaryKey(id);
	}

	// 更新或保存
	@Override
	public int saveOrUpdate(Hospital hospital, Integer type) {
		if (null != hospital.getId() && hospital.getId() > 0) {
			if (hospitalDao.selectByPrimaryKey(hospital.getId()).getPwd().equals(hospital.getPwd())) {
				return hospitalDao.updateByPrimaryKeySelective(hospital);
			} else {
				hospital.setPwd(EncodePassword.encodePassword(hospital.getPwd()));
				return hospitalDao.updateByPrimaryKeySelective(hospital);
			}
		} else {
			hospital.setPwd(EncodePassword.encodePassword(hospital.getPwd()));
			hospital.setAddtime((int) (new Date().getTime() / 1000));
			hospital.setRegType(type);
			hospital.setIsDel(false);
			return hospitalDao.insertSelective(hospital);
		}

	}

	@Override
	public int delById(Integer id) {
		return hospitalDao.delById(id);
	}

	@Override
	public int delByIds(Integer[] ids) {
		return hospitalDao.delByIds(ids);
	}

	@Override
	public int upById(Integer id) {
		return hospitalDao.upById(id);
	}

	@Override
	public int upByIds(Integer[] ids) {
		return hospitalDao.upByIds(ids);
	}
	//查询所有
	@Override
	public List<Hospital> findAll() {
		HospitalQuery hospitalQuery = new HospitalQuery();
		hospitalQuery.setFields("id,hos_name");
		Criteria createCriteria = hospitalQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		return hospitalDao.selectByExample(hospitalQuery);
	}

}
