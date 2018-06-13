package com.lwyykj.core.solr.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.product.Product;
import com.lwyykj.core.bean.product.Sku;
import com.lwyykj.core.dao.product.ProductDao;
import com.lwyykj.core.dao.product.SkuDao;
import com.lwyykj.core.solr.service.SolrService;

/**
 * 
 * @author yl solr封装服务
 *
 */
@Service("solrService")
@Transactional
public class SolrServiceImpl implements SolrService {
	// 注入solrClient
	@Resource
	private SolrClient solrClient;
	@Resource
	private ProductDao productDao;
	@Resource
	private SkuDao skuDao;

	// 单存
	@Override
	public int saveSolr(Product product) {
		int result = 0;
		try {
			solrClient.addBean(product);
			solrClient.commit();
			result = 1;
		} catch (IOException e) {
			System.out.println("IO操作失败!");
			result = -1;
			e.printStackTrace();
		} catch (SolrServerException e) {
			System.out.println("Solr操作失败!");
			result = -2;
			e.printStackTrace();
		}
		return result;
	}

	// 批量上传提交
	@Override
	public int saveSolrS(List<Product> products) {
		int result = 0;
		try {
			solrClient.addBeans(products);
			solrClient.commit();
			result=1;
		} catch (SolrServerException e) {
			System.out.println("Solr操作失败!");
			result = -2;
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO操作失败!");
			result = -1;
			e.printStackTrace();
		}
		return result;
	}

	// 单删
	@Override
	public int delSolr(Integer id) {
		int result = 0;
		try {
			solrClient.deleteById(String.valueOf(id));
			solrClient.commit();
//			System.out.println("执行了删除");
			result = 1;
		} catch (SolrServerException e) {
			System.out.println("solr操作失败!");
			result = -2;
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO操作失败!");
			result = -1;
			e.printStackTrace();
		}
		return result;
	}

	// 批量删除
	@Override
	public int delSolrs(Integer[] ids) {
		int result = 0;
		List<String> list = new ArrayList<>();
		for (Integer id : ids) {
			list.add(String.valueOf(id));
		}
		try {
			solrClient.deleteById(list);
			solrClient.commit();
			result=1;
		} catch (SolrServerException e) {
			System.out.println("Solr操作失败!");
			result = -2;
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO操作失败!");
			result = -1;
			e.printStackTrace();
		}
		return result;
	}

	// 新增并提交
	@Override
	public int applyNew(Integer id) {
		int result = 0;
		Product product = productDao.selectByPrimaryKey(id);
		List<Sku> skus = skuDao.selectByPid(id);
		if (null != skus && skus.size() > 0) {
			product.setPrice(skus.get(0).getPrice());
		} else {
			product.setPrice(0);
		}
		try {
			solrClient.addBean(product);
			solrClient.commit();
//			System.out.println("执行了添加");
			result = 1;
		} catch (IOException e) {
			System.out.println("IO操作失败!");
			result = -1;
			e.printStackTrace();
		} catch (SolrServerException e) {
			System.out.println("Solr操作失败!");
			result = -2;
			e.printStackTrace();
		}
		return result;
	}

}
