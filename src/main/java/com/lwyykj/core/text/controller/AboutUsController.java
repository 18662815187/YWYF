package com.lwyykj.core.text.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwyykj.core.bean.text.AboutUs;
import com.lwyykj.core.text.service.AboutUsService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("AboutUs")
public class AboutUsController {
@Resource
private AboutUsService aboutUsService;
	// 列表
	@RequestMapping("/list.do")
	public String list(String keyword, Integer pageNo, Model model) {
		Pagination pagination = aboutUsService.selectByPagination(keyword, pageNo);
		model.addAttribute("pagination", pagination);
		model.addAttribute("keyword", keyword);
		return "/AboutUs/list";
	}

	// 去新增或编辑页
	@RequestMapping("/init")
	public String init(Integer id, Model model) {
		if (null != id) {
			AboutUs aboutUs = aboutUsService.selectByPrimaryKey(id);
			System.out.println(aboutUs);
			model.addAttribute("aboutUs", aboutUs);
		}
		return "/AboutUs/edit";
	}

	// 提交或更新
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(AboutUs aboutUs, Model model) {
		if (null != aboutUs.getTitle()) {
			int result = aboutUsService.apply(aboutUs);
			if (result > 0) {
				return "redirect:list.do";
			}
		} else {
			model.addAttribute("msg", "标题不能为空！");
		}
		model.addAttribute("aboutUs", aboutUs);
		return "/AboutUs/edit";
	}

	// 批删
	@RequestMapping("/deletes")
	public String deletes(Integer[] ids) {
		
		int result = aboutUsService.delByIds(ids);
		if (result > 0) {
			return "redirect:list.do";
		}
	
		return null;
	}
}
