package com.lwyykj.core.text.service;

import java.util.List;

import com.lwyykj.core.bean.text.MailFee;

import cn.itcast.common.page.Pagination;

public interface MailFeeService {
	List<MailFee> selectAll(Integer phId);

	Pagination selectByPagination(String keyword, Integer pageNo);

	MailFee selectByPrimaryKey(Integer id);

	// 保存更新
	int saveOrUpdate(MailFee mailFee);

	int delByIds(Integer[] ids);
}
