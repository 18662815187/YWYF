package com.lwyykj.core.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.user.Admin;
import com.lwyykj.core.dao.user.AdminDao;
import com.lwyykj.core.login.service.AdminService;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
	@Resource
	private AdminDao adminDao;

	@Override
	public Admin findByTel(String userName) {
		return adminDao.findByTel(userName);
	}

}
