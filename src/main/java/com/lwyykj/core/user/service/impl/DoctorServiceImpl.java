package com.lwyykj.core.user.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.common.EncodePassword;
import com.lwyykj.core.bean.doctor.DocHosQuery;
import com.lwyykj.core.bean.doctor.DocHosQuery.Criteria;
import com.lwyykj.core.bean.doctor.Doctor;
import com.lwyykj.core.bean.doctor.DoctorQuery;
import com.lwyykj.core.dao.doctor.DocHosDao;
import com.lwyykj.core.dao.doctor.DoctorDao;
import com.lwyykj.core.user.service.DoctorService;
import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.3.19
 *
 */
@Service("doctorService")
@Transactional
public class DoctorServiceImpl implements DoctorService {
	@Resource
	private DocHosDao docHosDao;
	@Resource
	private DoctorDao doctorDao;

	// 列表
	@Override
	public Pagination selectByPagination(Integer pageNo, String docName, String hosName, String provincesId,
			String cityId, String area, Boolean isShow) {
		StringBuilder params = new StringBuilder();
		DocHosQuery docHosQuery = new DocHosQuery();
		docHosQuery.setOrderByClause("id desc");
		docHosQuery.setPageSize(20);
		docHosQuery.setPageNo(Pagination.cpn(pageNo));
		Criteria createCriteria = docHosQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		if (null != docName) {
			createCriteria.andNameLike("%" + docName + "%");
			params.append("docName=").append(docName);
		}
		if (null != hosName) {
			createCriteria.andHosNameLike("%" + hosName + "%");
			params.append("&hosName=").append(hosName);
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
		String url = "doctor/list.do";
		Pagination pagination = new Pagination(docHosQuery.getPageNo(), docHosQuery.getPageSize(),
				docHosDao.countByExample(docHosQuery), docHosDao.selectByExample(docHosQuery));
		pagination.pageView(url, params.toString());
		return pagination;
	}
	//通过主键查询
	@Override
	public Doctor findById(Integer id) {
		return doctorDao.selectByPrimaryKey(id);
	}
	//更新或新增提交
	@Override
	public int saveOrUpdate(Doctor doctor, Integer editType) {
		if (null != doctor.getId() && doctor.getId() > 0) {
			Doctor doctor2 = doctorDao.selectByPrimaryKey(doctor.getId());
			if (doctor2.getPwd().equals(doctor.getPwd())) {
				return doctorDao.updateByPrimaryKeySelective(doctor);
			} else {
				doctor.setPwd(EncodePassword.encodePassword(doctor.getPwd()));
				return doctorDao.updateByPrimaryKeySelective(doctor);
			}
		} else {
			doctor.setPwd(EncodePassword.encodePassword(doctor.getPwd()));
			doctor.setRegtime((int) (new Date().getTime() / 1000));
			doctor.setType(editType);
			doctor.setIsDel(false);
			return doctorDao.insertSelective(doctor);
		}
		
	}
	//批量删除
	@Override
	public int delByIds(Integer[] ids) {
		return doctorDao.delByIds(ids);
	}
	//批量审核
	@Override
	public int upByIds(Integer[] ids) {
		return doctorDao.upByIds(ids);
	}
	//查询所有
	@Override
	public List<Doctor> findAll() {
		DoctorQuery doctorQuery = new DoctorQuery();
		doctorQuery.setFields("id,name");
		return doctorDao.selectByExample(doctorQuery);
	}

}
