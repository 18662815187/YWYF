package com.lwyykj.core.product.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.product.Brand;
import com.lwyykj.core.bean.product.BrandFacQuery;
import com.lwyykj.core.bean.product.BrandFacQuery.Criteria;
import com.lwyykj.core.bean.product.BrandQuery;
import com.lwyykj.core.dao.product.BrandDao;
import com.lwyykj.core.dao.product.BrandFacDao;
import com.lwyykj.core.product.service.BrandService;
import cn.itcast.common.page.Pagination;

@Service("brandService")
@Transactional
public class BrandServiceImpl implements BrandService {
	@Resource
	private BrandDao brandDao;
	@Resource
	private BrandFacDao brandFacDao;

	// 查询所有品牌用于新增和编辑
	@Override
	public List<Brand> findAll() {
		BrandQuery brandQuery = new BrandQuery();
		com.lwyykj.core.bean.product.BrandQuery.Criteria createCriteria = brandQuery.createCriteria();
		createCriteria.andIsShowEqualTo(true);
		createCriteria.andIsDelEqualTo(false);
		return brandDao.selectByExample(brandQuery);
	}
	//新增或更新
	@Override
	public int saveOrUpdate(Brand brand) {
		if (brand.getId() != null && brand.getId() > 0) {
			return brandDao.updateByPrimaryKey(brand);
		}
		// 使用redis生成id
//		Jedis jedis = jedisPool.getResource();
//		Long id = jedis.incr("bno");
//		brand.setId(id.intValue());
//		jedis.hset("brand", String.valueOf(brand.getId()), brand.getBrandName());
//		jedis.bgsave();
//		jedis.flushAll();
//		jedis.close();
		brand.setIsDel(false);
		brand.setIsShow(true);
		brand.setAddtime((int)(new Date().getTime()/1000));
		return brandDao.insertSelective(brand);
	}

	@Override
	public int delById(Integer id) {
		return brandDao.DelByid(id);
	}

	@Override
	public int delByIds(Integer[] ids) {
		return brandDao.deletes(ids);
	}

	// 列表带分页
	@Override
	public Pagination selectByPagination(String Brand_name, Integer pageNo, String fac_name, Boolean isShow) {
		BrandFacQuery brandFacQuery = new BrandFacQuery();
		StringBuilder params = new StringBuilder();
		brandFacQuery.setPageSize(20);
		brandFacQuery.setOrderByClause("id desc");
		brandFacQuery.setPageNo(Pagination.cpn(pageNo));
		Criteria createCriteria = brandFacQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		if (null != Brand_name) {
			createCriteria.andBrandNameLike("%" + Brand_name + "%");
			params.append("Brand_name=").append(Brand_name);
		}
		if (null != fac_name) {
			createCriteria.andFacNameLike("%" + fac_name + "%");
			params.append("&fac_name=").append(fac_name);
		}
		if (null != isShow) {
			createCriteria.andIsShowEqualTo(isShow);
			params.append("&isShow=").append(isShow);
		} else {
			createCriteria.andIsShowEqualTo(true);
			params.append("&isShow=").append(isShow);
		}
		String url = "brand/list.do";
		Pagination pagination = new Pagination(brandFacQuery.getPageNo(), brandFacQuery.getPageSize(),
				brandFacDao.countByExample(brandFacQuery), brandFacDao.selectByExample(brandFacQuery));
		pagination.pageView(url, params.toString());
		return pagination;
	}

	// 根据主键查询
	@Override
	public Brand findById(Integer id) {
		return brandDao.selectByPrimaryKey(id);
	}
	//批量已审
	@Override
	public int upByIds(Integer[] ids) {
		return brandDao.upByIds(ids);
	}

}
