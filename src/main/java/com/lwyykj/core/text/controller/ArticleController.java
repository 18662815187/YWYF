package com.lwyykj.core.text.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lwyykj.core.bean.text.Article;
import com.lwyykj.core.text.service.ArticleService;
import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.4.17 文章通知
 *
 */

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Resource
	private ArticleService articleService;

	// 列表
	@RequestMapping("/list.do")
	public String list(String keyword, Integer pageNo, Model model) {
		Pagination pagination = articleService.selectByPagination(keyword, pageNo);
		model.addAttribute("pagination", pagination);
		model.addAttribute("keyword", keyword);
		return "/Article/list";
	}

	// 去新增或编辑页
	@RequestMapping("/init")
	public String init(Integer id, Model model) {
		if (null != id) {
			Article article = articleService.selectByPrimaryKey(id);
			model.addAttribute("article", article);
		}
		return "/Article/edit";
	}

	// 提交或更新
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Article article, Model model) {
		if (null != article.getTitle()) {
			int result = articleService.apply(article);
			if (result > 0) {
				return "redirect:list.do";
			}
		} else {
			model.addAttribute("msg", "标题不能为空！");
		}
		model.addAttribute("article", article);
		return "/Article/edit";
	}

	// 批删
	@RequestMapping("/deletes")
	public String deletes(Integer[] ids) {
		int result = articleService.delByIds(ids);
		if (result > 0) {
			return "redirect:list.do";
		}
		return null;
	}

	// 批量显示
	@RequestMapping("/show")
	public String show(Integer[] ids) {
		int result = articleService.show(ids);
		if (result > 0) {
			return "redirect:list.do";
		}
		return null;
	}
	//批量禁止显示
	@RequestMapping("/unShow")
	public String unShow(Integer[] ids) {
		int result = articleService.unShow(ids);
		if (result > 0) {
			return "redirect:list.do";
		}
		return null;
	}
}
