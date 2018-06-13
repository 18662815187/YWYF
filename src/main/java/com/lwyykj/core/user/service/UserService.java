package com.lwyykj.core.user.service;

import java.util.List;

import com.lwyykj.core.bean.user.User;

import cn.itcast.common.page.Pagination;

public interface UserService {
	//列表
	public Pagination selectByPagination(Integer pageNo,String name,String tel,Integer status);
	//查询所有
	List<User> findAll();
	//根据ID查询
	User findById(Integer id);
	//保存更新
	Integer saveOrUpdate(User user,Integer type);
	//冻结
	int delById(Integer id);
	//批量冻结
	int delByIds(Integer[] ids);
	//批量审核
	int auditByIds(Integer[] ids);
	//批量屏蔽
	int shieldByIds(Integer[] ids);
}
