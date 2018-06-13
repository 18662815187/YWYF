package com.lwyykj.core.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.common.EncodePassword;
import com.lwyykj.core.bean.pharmacy.PhHosQuery;
import com.lwyykj.core.bean.pharmacy.Pharmacy;
import com.lwyykj.core.bean.pharmacy.PharmacyQuery;
import com.lwyykj.core.bean.pharmacy.PharmacyQuery.Criteria;
import com.lwyykj.core.dao.doctor.HospitalDao;
import com.lwyykj.core.dao.pharmacy.PhHosDao;
import com.lwyykj.core.dao.pharmacy.PharmacyDao;
import com.lwyykj.core.user.service.PharmacyService;
import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.3.15
 *
 */
@Service("pharmacyService")
@Transactional
public class PharmacyServiceImpl implements PharmacyService {
	@Resource
	private PharmacyDao pharmacyDao;
	@Resource
	private HospitalDao hospitalDao;
	@Resource
	private PhHosDao phHosDao;

	// 列表
	@Override
	public Pagination selectByPagination(Integer pageNo, String hosName, String phName, String provincesId,
			String cityId, String area, Boolean isShow) {
		StringBuilder params = new StringBuilder();
		PhHosQuery phHosQuery = new PhHosQuery();
		phHosQuery.setPageNo(Pagination.cpn(pageNo));
		phHosQuery.setPageSize(20);
		phHosQuery.setOrderByClause("id desc");
		com.lwyykj.core.bean.pharmacy.PhHosQuery.Criteria createCriteria = phHosQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		if (null != phName) {
			createCriteria.andPhnameLike("%" + phName + "%");
			params.append("phName=").append(phName);
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
		if (null != hosName) {
			createCriteria.andDisHosNameLike("%" + hosName + "%");
			params.append("&hosName=").append(hosName);
		}
		Pagination pagination = new Pagination(phHosQuery.getPageNo(), phHosQuery.getPageSize(),
				phHosDao.countByExample(phHosQuery), phHosDao.selectByExample(phHosQuery));
		String url = "pharmacy/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}

	@Override
	public Pharmacy findById(Integer id) {
		return pharmacyDao.selectByPrimaryKey(id);
	}

	// 更新或保存
	@Override
	public int saveOrUpdate(Pharmacy pharmacy, Integer editType) {
		if (null!=pharmacy.getId()&&pharmacy.getId()>0) {
			if (pharmacy.getPwd().equals(pharmacyDao.selectByPrimaryKey(pharmacy.getId()).getPwd())) {
				return pharmacyDao.updateByPrimaryKeySelective(pharmacy);
			} else {
				pharmacy.setPwd(EncodePassword.encodePassword(pharmacy.getPwd()));
				return pharmacyDao.updateByPrimaryKeySelective(pharmacy);
			}
		} else {
			pharmacy.setPwd(EncodePassword.encodePassword(pharmacy.getPwd()));
			pharmacy.setAddtime((int) (new Date().getTime() / 1000));
			pharmacy.setIsDel(false);
			pharmacy.setRegType(editType);
			return pharmacyDao.insertSelective(pharmacy);
		}
		
	}
	

	@Override
	public int delByIds(Integer[] ids) {
		return pharmacyDao.delByIds(ids);
	}


	@Override
	public int upByIds(Integer[] ids) {
		return pharmacyDao.upByIds(ids);
	}

	// 查询所有
	@Override
	public List<Pharmacy> findAll() {
		PharmacyQuery pharmacyQuery = new PharmacyQuery();
		pharmacyQuery.setFields("id,pharmacy_name");
		Criteria createCriteria = pharmacyQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		List<Pharmacy> pharmaciese = pharmacyDao.selectByExample(pharmacyQuery);
		return pharmaciese;
	}

}
