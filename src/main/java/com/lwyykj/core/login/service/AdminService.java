package com.lwyykj.core.login.service;

import com.lwyykj.core.bean.user.Admin;

public interface AdminService {
	Admin findByTel(String userName);
}
