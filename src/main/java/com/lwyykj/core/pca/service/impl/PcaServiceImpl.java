package com.lwyykj.core.pca.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.pca.Areas;
import com.lwyykj.core.bean.pca.AreasQuery;
import com.lwyykj.core.bean.pca.Cities;
import com.lwyykj.core.bean.pca.CitiesQuery;
import com.lwyykj.core.bean.pca.CitiesQuery.Criteria;
import com.lwyykj.core.bean.pca.Provinces;
import com.lwyykj.core.bean.pca.ProvincesQuery;
import com.lwyykj.core.dao.pca.AreasDao;
import com.lwyykj.core.dao.pca.CitiesDao;
import com.lwyykj.core.dao.pca.ProvincesDao;
import com.lwyykj.core.pca.service.PcaService;

@Service("pcaService")
@Transactional
public class PcaServiceImpl implements PcaService {
	@Resource
	private ProvincesDao provincesDao;
	@Resource
	private CitiesDao citiesDao;
	@Resource
	private AreasDao areasDao;

	@Override
	public List<Provinces> findAll() {
		ProvincesQuery provincesQuery = new ProvincesQuery();
		List<Provinces> provinces = provincesDao.selectByExample(provincesQuery);
		for (Provinces provinces2 : provinces) {
			List<Cities> cities = citiesDao.findByPid(provinces2.getProvinceid());
			for (Cities cities2 : cities) {
				cities2.setAreas(areasDao.findByCid(cities2.getCityid()));
			}
			provinces2.setCities(cities);
		}
		return provinces;
	}

	@Override
	public List<Cities> findByPid(String pid) {
		CitiesQuery citiesQuery = new CitiesQuery();
		Criteria createCriteria = citiesQuery.createCriteria();
		createCriteria.andProvinceidEqualTo(pid);
		return citiesDao.selectByExample(citiesQuery);
	}

	@Override
	public List<Areas> findByCid(String cid) {
		AreasQuery areasQuery = new AreasQuery();
		com.lwyykj.core.bean.pca.AreasQuery.Criteria createCriteria = areasQuery.createCriteria();
		createCriteria.andCityidEqualTo(cid);
		return areasDao.selectByExample(areasQuery);
	}

}
