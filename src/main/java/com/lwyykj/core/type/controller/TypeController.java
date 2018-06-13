package com.lwyykj.core.type.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lwyykj.core.bean.type.ProType;
import com.lwyykj.core.type.service.FunTypeService;
import com.lwyykj.core.type.service.TypeService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/type")
public class TypeController {
	@Resource
	private TypeService typeService;
	@Resource
	private FunTypeService funTypeService;

	// 列表
	@RequestMapping("/list.do")
	public String selectByPagination(Integer pageNo, String typeName, Model model) {
		Pagination pagination = typeService.selectByPagination(pageNo, typeName);
		model.addAttribute("pagination", pagination);
		model.addAttribute("typeName", typeName);
		return "/type/list";
	}

	// 去添加页或编辑页
	@RequestMapping("/toAddOrEdit")
	public String init(Integer id, Model model) {
		List<ProType> proTypes = typeService.queryAll();
		model.addAttribute("types", proTypes);
		if (null != id) {
			ProType proType = typeService.queryById(id);
			model.addAttribute("type", proType);
		}
		return "/type/edit";
	}

	// 提交
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(ProType proType, Model model) {
		if (null != proType.getTypeName()) {
			int a = typeService.saveOrUpdate(proType);
			if (a > 0) {
				return "redirect:list.do";
			} else {
				model.addAttribute("type", proType);
				model.addAttribute("msg", "更新失败请，请联系管理员！");
				return "/type/edit";
			}
		}
		model.addAttribute("type", proType);
		model.addAttribute("msg", "请填写分类名称！");
		return "/type/edit";
	}

	// 删除单删
	@RequestMapping("/delById")
	public String delById(Integer id) {
		int a = typeService.delById(id);
		if (a > 0) {
			return "redirect:list.do";
		}
		return null;
	}

	// 批量删除
	@RequestMapping("/delByIds")
	public String delByIds(Integer[] ids) {
		int a = typeService.delByIds(ids);
		if (a > 0) {
			return "redirect:list.do";
		}
		return null;
	}
}
