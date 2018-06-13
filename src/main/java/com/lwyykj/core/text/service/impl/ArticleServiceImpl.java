package com.lwyykj.core.text.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.text.Article;
import com.lwyykj.core.bean.text.ArticleQuery;
import com.lwyykj.core.bean.text.ArticleQuery.Criteria;
import com.lwyykj.core.dao.text.ArticleDao;
import com.lwyykj.core.text.service.ArticleService;

import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.4.17 文章， 通知
 *
 */
@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleDao articleDao;

	// 列表页
	@Override
	public Pagination selectByPagination(String keyword, Integer pageNo) {
		StringBuilder params = new StringBuilder();
		ArticleQuery articleQuery = new ArticleQuery();
		articleQuery.setPageNo(Pagination.cpn(pageNo));
		articleQuery.setFields("id, title, pic, addtime,is_show");
		articleQuery.setPageSize(20);
		Criteria createCriteria = articleQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		if (null != keyword) {
			createCriteria.andTitleLike("%" + keyword + "%");
			params.append("keyword=").append(keyword);
		}
		String url = "article/list.do";
		Pagination pagination = new Pagination(articleQuery.getPageNo(), articleQuery.getPageSize(),
				articleDao.countByExample(articleQuery), articleDao.selectByExample(articleQuery));
		pagination.pageView(url, params.toString());
		return pagination;
	}

	// 根据主键查询
	@Override
	public Article selectByPrimaryKey(Integer id) {
		Article article = articleDao.selectByPrimaryKey(id);
		return article;
	}

	// 提交或更新
	@Override
	public int apply(Article article) {
		int result = 0;
		if (null != article.getId() && article.getId() > 0) {
			result = articleDao.updateByPrimaryKeySelective(article);
		} else {
			article.setAddtime((int)(new Date().getTime()/1000));
			article.setIsDel(false);
			result = articleDao.insertSelective(article);
		}
		return result;
	}
	//批删
	@Override
	public int delByIds(Integer[] ids) {
		return articleDao.deletes(ids);
	}
	//批量显示
	@Override
	public int show(Integer[] ids) {
		return articleDao.showAll(ids);
	}
	//批量禁止显示
	@Override
	public int unShow(Integer[] ids) {
		return articleDao.unShowAll(ids);
	}

}
