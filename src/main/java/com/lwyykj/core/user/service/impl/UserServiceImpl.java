package com.lwyykj.core.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.common.EncodePassword;
import com.lwyykj.core.bean.user.User;
import com.lwyykj.core.bean.user.UserQuery;
import com.lwyykj.core.bean.user.UserQuery.Criteria;
import com.lwyykj.core.dao.user.UserDao;
import com.lwyykj.core.user.service.UserService;
import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.3.13
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	// 列表
	@Override
	public Pagination selectByPagination(Integer pageNo, String name, String tel, Integer status) {
		StringBuilder params = new StringBuilder();
		UserQuery userQuery = new UserQuery();
		userQuery.setPageNo(Pagination.cpn(pageNo));
		userQuery.setOrderByClause("id desc");
		userQuery.setPageSize(20);
		Criteria createCriteria = userQuery.createCriteria();
		if (null != name) {
			createCriteria.andNicknameLike("%" + name + "%");
			params.append("name=").append(name);
		}
		if (null != tel) {
			createCriteria.andTelLike("%" + tel + "%");
			params.append("&tel=").append(tel);
		}
		if (null != status) {
			createCriteria.andStatusEqualTo(status);
			params.append("&status=").append(status);
		}
		String url = "user/list.do";
		Pagination pagination = new Pagination(userQuery.getPageNo(), userQuery.getPageSize(),
				userDao.countByExample(userQuery), userDao.selectByExample(userQuery));
		pagination.pageView(url, params.toString());
		return pagination;
	}

	// 查询所有
	@Override
	public List<User> findAll() {
		UserQuery userQuery = new UserQuery();
		return userDao.selectByExample(userQuery);
	}

	// 根据主键查询
	@Override
	public User findById(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}

	// 保存或更新
	@Override
	public Integer saveOrUpdate(User user, Integer type) {
		if (null != user.getId() && user.getId() > 0) {
			User user2 = userDao.selectByPrimaryKey(user.getId());
			if (user2.getPwd().equals(user.getPwd())) {
				return userDao.updateByPrimaryKeySelective(user);
			} else {
				user.setPwd(EncodePassword.encodePassword(user.getPwd()));
				return userDao.updateByPrimaryKeySelective(user);
			}
		} else {
			user.setPwd(EncodePassword.encodePassword(user.getPwd()));
			user.setAddtime((int) (new Date().getTime() / 1000));
			user.setAddType(type);
			if (null == user.getStatus()) {
				user.setStatus(0);
			}
			// TODO 预留环信帐号设置
			return userDao.insertSelective(user);
		}

	}

	// 根据主键冻结
	@Override
	public int delById(Integer id) {
		return userDao.upById(id);
	}

	// 批量冻结
	@Override
	public int delByIds(Integer[] ids) {
		return userDao.upByIds(ids);
	}
	// 批量审核

	@Override
	public int auditByIds(Integer[] ids) {
		return userDao.auditByIds(ids);
	}
	//批量屏蔽
	@Override
	public int shieldByIds(Integer[] ids) {
		return userDao.shieldByIds(ids);
	}

}
