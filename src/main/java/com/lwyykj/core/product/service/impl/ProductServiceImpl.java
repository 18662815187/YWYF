package com.lwyykj.core.product.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lwyykj.core.bean.product.FacProQuery;
import com.lwyykj.core.bean.product.FacProQuery.Criteria;
import com.lwyykj.core.dao.product.FacProDao;
import com.lwyykj.core.dao.product.ProductDao;
import com.lwyykj.core.dao.product.SkuDao;
import com.lwyykj.core.bean.product.Product;
import com.lwyykj.core.bean.product.Sku;
import com.lwyykj.core.product.service.ProductService;
import com.lwyykj.core.solr.service.SolrService;

import cn.itcast.common.page.Pagination;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	@Resource
	private FacProDao facProDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private SkuDao skuDao;
	@Resource
	private SolrClient solrClient;
	//此处时间紧迫直接注入了一个service，时间充足时可以换成通用工具类，注入service不推荐使用
	@Resource
	private SolrService solrService;

	// 列表带分页
	@Override
	public Pagination selectAllProduct(Integer pageNo, String pro_name, String ph_name, Boolean isShow) {
		FacProQuery facProQuery = new FacProQuery();
		facProQuery.setOrderByClause("pro_id desc");
		facProQuery.setPageNo(Pagination.cpn(pageNo));
		facProQuery.setPageSize(20);
		Criteria createCriteria = facProQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		StringBuilder params = new StringBuilder();
		if (null != pro_name) {
			createCriteria.andProNameLike("%" + pro_name + "%");
			params.append("pro_name=").append(pro_name);
		}
		if (null != ph_name) {
			createCriteria.andPhNameLike("%" + ph_name + "%");
			params.append("&ph_name=").append(ph_name);
		}
		if (null != isShow) {
			createCriteria.andIsShowEqualTo(isShow);
			params.append("&isShow=").append(isShow);
		}
		Pagination pagination = new Pagination(facProQuery.getPageNo(), facProQuery.getPageSize(),
				facProDao.countByExample(facProQuery), facProDao.selectByExample(facProQuery));
		// 分页展示,需要传入链接和拼接条件
		String url = "product/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}

	// 按主键查询
	@Override
	public Product selectById(Integer id) {
		return productDao.selectByPrimaryKey(id);
	}

	// 批删
	@Override
	public int delByIds(Integer[] ids) {
		for (Integer id : ids) {
			solrService.delSolr(id);
		}
		return productDao.deletes(ids);
	}

	// 保存或更新
	@Override
	public int saveOrUpdate(Product product) {
		int result = 0;
		if (null != product.getId() && product.getId() > 0) {
			if (product.getIsShow()) {
				solrService.saveSolr(product);
				result = productDao.updateByPrimaryKeySelective(product);
			}else{
				solrService.delSolr(product.getId());
				result = productDao.updateByPrimaryKeySelective(product);
			}
			return result;
		} else {
			product.setCreateTime((int) (new Date().getTime() / 1000));
			product.setIsDel(false);
			int b = productDao.insertSelective(product);
			if (b > 0) {
				String[] specs = product.getSpecs().split(",");
				for (String spec : specs) {
					Sku sku = new Sku();
					sku.setProId(product.getId());
					sku.setSpec(spec);
					sku.setMailFree(0d);
					sku.setIsFree(false);
					sku.setPrice(0d);
					//暂时使用产品录入时录入的运费
					sku.setDeliveFee(product.getFreight());
					sku.setStock(0);
					sku.setSuperPrice(0d);
					sku.setMarketPrice(0d);
					sku.setUpperLimit(0);
					int c = skuDao.insertSelective(sku);
					if (c > 0) {
						result = 1;
					}
				}
			} else {
				return result;
			}
			return result;
		}
	}

	// 批量上架
	@Override
	public int upByIds(Integer[] ids) {
		int result = 0;
		List<Product> products = productDao.selectByIds(ids);
		for (Product product : products) {
			List<Sku> skus = skuDao.selectByPid(product.getId());
			if (null != skus && skus.size() > 0) {
				Double price = skus.get(0).getPrice();
				product.setPrice(price);
			} else {
				product.setPrice(0);
			}
		}
		try {
			solrClient.addBeans(products);
			solrClient.commit();
			result = productDao.upByIds(ids);
		} catch (SolrServerException e) {
			System.out.println("solr操作失败!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO操作失败");
			e.printStackTrace();
		}
		return result;
	}

	// 批量下架
	@Override
	public int unShow(Integer[] ids) {
		int result = 0;
		List<String> list = new ArrayList<>();
		for (Integer id : ids) {
			list.add(String.valueOf(id));
		}
		try {
			solrClient.deleteById(list);
			solrClient.commit();
			result = productDao.unShowByIds(ids);
		} catch (SolrServerException e) {
			System.out.println("Solr操作失败");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO操作失败");
			e.printStackTrace();
		}
		return result;
	}

}
