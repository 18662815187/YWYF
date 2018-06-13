package com.lwyykj.core.text.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwyykj.core.bean.text.Drugstore;
import com.lwyykj.core.text.service.DrugstoreService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("drugstore")
public class DrugStoreController {

	@Resource
	private DrugstoreService drugstoreService;
	@RequestMapping("/list.do")
	public String list(String keyword, Integer pageNo, Model model) {
		Pagination pagination = drugstoreService.selectByPagination(keyword, pageNo);
		model.addAttribute("pagination", pagination);
		model.addAttribute("keyword", keyword);
		return "/drugstore/list";
	}

	// 去新增或编辑页
	@RequestMapping("/init")
	public String init(Integer id, Model model) {
		if (null != id) {
			Drugstore dugstore = drugstoreService.selectByPrimaryKey(id);
			model.addAttribute("dugstore", dugstore);
		}
		return "/drugstore/edit";
	}

	// 提交或更新
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Drugstore dugstore, Model model) {
		if (null != dugstore.getName()) {
			int result = drugstoreService.saveOrUpdate(dugstore);
			if (result > 0) {
				return "redirect:list.do";
			}
		} else {
			model.addAttribute("msg", "标题不能为空！");
		}
		model.addAttribute("dugstore", dugstore);
		return "/dugstore/edit";
	}

	// 批删
	@RequestMapping("/deletes")
	public String deletes(Integer[] ids) {
		int result = drugstoreService.delByIds(ids);
		if (result > 0) {
			return "redirect:list.do";
		}
		return null;
	}

	// 批量显示
	@RequestMapping("/show")
	public String show(Integer[] ids) {
		int result = drugstoreService.show(ids);
		if (result > 0) {
			return "redirect:list.do";
		}
		return null;
	}
	//批量禁止显示
	@RequestMapping("/unShow")
	public String unShow(Integer[] ids) {
		int result = drugstoreService.unShow(ids);
		if (result > 0) {
			return "redirect:list.do";
		}
		return null;
	}
}
