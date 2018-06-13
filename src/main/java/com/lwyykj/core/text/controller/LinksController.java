package com.lwyykj.core.text.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwyykj.core.bean.text.Links;
import com.lwyykj.core.text.service.LinksService;

import cn.itcast.common.page.Pagination;

/**
 * 
 * @author cxk 20180423 友链
 *
 */
@Controller
@RequestMapping("/links")
public class LinksController {
	@Resource
	private LinksService linksService;

	// 列表
	@RequestMapping("/list.do")
	public String list(String keyword, Integer pageNo, Model model) {
		Pagination pagination = linksService.selectByPagination(keyword, pageNo);
		model.addAttribute("pagination", pagination);
		model.addAttribute("keyword", keyword);
		return "/Links/list";
	}

	// 去新增或编辑页
	@RequestMapping("/init")
	public String init(Integer id, Model model) {
		if (null != id) {
			Links link = linksService.selectByPrimaryKey(id);
			model.addAttribute("link", link);
		}
		return "/Links/edit";
	}

	// 提交或更新
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Links links, Model model) {
		if (null != links.getLinkName()) {
			int result = linksService.saveOrUpdate(links);
			if (result > 0) {
				return "redirect:list.do";
			}
		} else {
			model.addAttribute("msg", "标题不能为空！");
		}
		System.out.println(links);
		model.addAttribute("links", links);
		return "/Links/edit";
	}

	// 批删
	@RequestMapping("/deletes")
	public String deletes(Integer[] ids) {
		int result = linksService.delByIds(ids);
		if (result > 0) {
			return "redirect:list.do";
		}
		return null;
	}
	// 批量解禁
		@RequestMapping("/show")
		public String show(Integer[] ids) {
			int result = linksService.show(ids);
			if (result > 0) {
				return "redirect:list.do";
			}
			return null;
		}
		//批量禁用
		@RequestMapping("/unShow")
		public String unShow(Integer[] ids) {
			int result = linksService.unShow(ids);
			if (result > 0) {
				return "redirect:list.do";
			}
			return null;
		}
}
