package com.lwyykj.core.loginRecord.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.record.AdminLoginRecord;
import com.lwyykj.core.bean.record.AdminLoginRecordQuery;
import com.lwyykj.core.bean.record.AdminLoginRecordQuery.Criteria;
import com.lwyykj.core.dao.record.AdminLoginRecordDao;
import com.lwyykj.core.loginRecord.service.AdminLoginRecordService;

@Service("adminLoginRecordService")
@Transactional
public class AdminLoginRecordServiceImpl implements AdminLoginRecordService {
	@Resource
	private AdminLoginRecordDao adminLoginRecordDao;

	@Override
	public AdminLoginRecord findById(Integer id) {
		AdminLoginRecordQuery adminLoginRecordQuery = new AdminLoginRecordQuery();
		adminLoginRecordQuery.setOrderByClause("id desc");
		Criteria createCriteria = adminLoginRecordQuery.createCriteria();
		createCriteria.andUidEqualTo(id);
		List<AdminLoginRecord> adminLoginRecords = adminLoginRecordDao.selectByExample(adminLoginRecordQuery);
		if (null != adminLoginRecords && adminLoginRecords.size() > 0) {
			return adminLoginRecords.get(0);
		}
		return null;
	}

	@Override
	public Integer insertLoginRecord(AdminLoginRecord adminLoginRecord) {

		return adminLoginRecordDao.insertSelective(adminLoginRecord);
	}

}
