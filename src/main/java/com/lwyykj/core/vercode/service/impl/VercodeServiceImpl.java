package com.lwyykj.core.vercode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.utils.Vercode;
import com.lwyykj.core.bean.utils.VercodeQuery;
import com.lwyykj.core.bean.utils.VercodeQuery.Criteria;
import com.lwyykj.core.dao.utils.VercodeDao;
import com.lwyykj.core.vercode.service.VercodeService;
@Service("vercodeService")
@Transactional
public class VercodeServiceImpl implements VercodeService {
	@Resource
	private VercodeDao vercodeDao;

	@Override
	public Vercode selectByTel(String tel) {
		VercodeQuery vercodeQuery = new VercodeQuery();
		Criteria createCriteria = vercodeQuery.createCriteria();
		createCriteria.andTelEqualTo(tel);
		List<Vercode> selectByExample = vercodeDao.selectByExample(vercodeQuery);
		if (null != selectByExample && selectByExample.size() > 0) {
			return selectByExample.get(0);
		}
		return null;
	}

}
