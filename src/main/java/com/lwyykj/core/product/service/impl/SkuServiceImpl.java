package com.lwyykj.core.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.product.Product;
import com.lwyykj.core.bean.product.Sku;
import com.lwyykj.core.bean.product.SkuQuery;
import com.lwyykj.core.bean.product.SkuQuery.Criteria;
import com.lwyykj.core.dao.product.ProductDao;
import com.lwyykj.core.dao.product.SkuDao;
import com.lwyykj.core.product.service.SkuService;
import com.lwyykj.core.solr.service.SolrService;

/**
 * 
 * @author yl 2018.3.20
 *
 */
@Service("skuService")
@Transactional
public class SkuServiceImpl implements SkuService {

	@Resource
	private SkuDao skuDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private SolrService solrService;

	// 根据产品ID查询
	@Override
	public List<Sku> findByPid(Integer pid) {
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.setOrderByClause("id desc");
		Criteria createCriteria = skuQuery.createCriteria();
		createCriteria.andProIdEqualTo(pid);
		createCriteria.andIsDelEqualTo(false);
		return skuDao.selectByExample(skuQuery);
	}

	// 新增
	@Override
	public int insert(Sku sku) {
		int result = 0;
		sku.setCreateTime((int) (new Date().getTime() / 1000));
		int a = skuDao.insertSelective(sku);
		if (a > 0) {
			Sku sku2 = skuDao.selectByPrimaryKey(sku.getId());
			List<String> spess = new ArrayList<>();
			SkuQuery skuQuery = new SkuQuery();
			skuQuery.setOrderByClause("id desc");
			Criteria createCriteria = skuQuery.createCriteria();
			createCriteria.andProIdEqualTo(sku2.getProId());
			createCriteria.andIsDelEqualTo(false);
			List<Sku> skus = skuDao.selectByExample(skuQuery);
			for (Sku sku3 : skus) {
				spess.add(sku3.getSpec());
			}
			String specs = StringUtils.join(spess.toArray(), ",");
			Product product = productDao.selectByPrimaryKey(sku2.getProId());
			product.setSpecs(specs);
			productDao.updateByPrimaryKey(product);
			if(product.getIsShow()){
				solrService.saveSolr(product);
			}
			result = 1;
		}

		return result;
	}

	// 更新
	@Override
	public int update(Sku sku) {
		int result = 0;
		int a = skuDao.updateByPrimaryKeySelective(sku);
		if (a > 0) {
			Sku sku2 = skuDao.selectByPrimaryKey(sku.getId());
			List<String> spess = new ArrayList<>();
			SkuQuery skuQuery = new SkuQuery();
			skuQuery.setOrderByClause("id desc");
			Criteria createCriteria = skuQuery.createCriteria();
			createCriteria.andProIdEqualTo(sku2.getProId());
			createCriteria.andIsDelEqualTo(false);
			List<Sku> skus = skuDao.selectByExample(skuQuery);
			for (Sku sku3 : skus) {
				spess.add(sku3.getSpec());
			}
			String specs = StringUtils.join(spess.toArray(), ",");
			Product product = productDao.selectByPrimaryKey(sku2.getProId());
			if(product.getIsShow()){
				solrService.saveSolr(product);
			}
			product.setSpecs(specs);
			productDao.updateByPrimaryKey(product);
			result = 1;
		}
		return result;
	}

	// 删除
	@Override
	public int delBySkuId(Integer skuId) {
		int result = 0;
		int a = skuDao.delBySkuId(skuId);
		if (a > 0) {
			Sku sku = skuDao.selectByPrimaryKey(skuId);
			List<String> spess = new ArrayList<>();
			SkuQuery skuQuery = new SkuQuery();
			skuQuery.setOrderByClause("id desc");
			Criteria createCriteria = skuQuery.createCriteria();
			createCriteria.andProIdEqualTo(sku.getProId());
			createCriteria.andIsDelEqualTo(false);
			List<Sku> skus = skuDao.selectByExample(skuQuery);
			for (Sku sku2 : skus) {
				spess.add(sku2.getSpec());
			}
			String specs = StringUtils.join(spess.toArray(), ",");
			Product product = productDao.selectByPrimaryKey(sku.getProId());
			product.setSpecs(specs);
			productDao.updateByPrimaryKey(product);
			if(product.getIsShow()){
				solrService.saveSolr(product);
			}
			result = 1;
		}
		return result;
	}

	// 根据ID查询
	@Override
	public Sku findById(Integer id) {
		return skuDao.selectByPrimaryKey(id);
	}

}
