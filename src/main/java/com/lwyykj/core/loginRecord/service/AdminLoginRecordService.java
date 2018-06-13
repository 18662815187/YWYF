package com.lwyykj.core.loginRecord.service;


import com.lwyykj.core.bean.record.AdminLoginRecord;

public interface AdminLoginRecordService {
	AdminLoginRecord findById(Integer id);
	
	Integer insertLoginRecord(AdminLoginRecord adminLoginRecord);
}
