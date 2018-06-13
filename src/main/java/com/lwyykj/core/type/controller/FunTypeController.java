package com.lwyykj.core.type.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lwyykj.core.bean.type.FunType;
import com.lwyykj.core.bean.type.ProType;
import com.lwyykj.core.type.service.FunTypeService;
import com.lwyykj.core.type.service.TypeService;

import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.3.9
 *
 */
@Controller
@RequestMapping("/funType")
public class FunTypeController {
	@Resource
	private FunTypeService funTypeService;
	@Resource
	private TypeService typeService;

	// 列表
	@RequestMapping("/list.do")
	public String selectByPagination(Integer pageNo, String FtName, Integer par_id, Model model) {
		List<ProType> proTypes = typeService.queryAll();
		Pagination pagination = funTypeService.selectByPagination(pageNo, FtName, par_id);
		model.addAttribute("pagination", pagination);
		model.addAttribute("FtName", FtName);
		model.addAttribute("par_id", par_id);
		model.addAttribute("proTypes", proTypes);
		return "/type/Funlist";
	}

	// 去添加或编辑页
	@RequestMapping("/toAddOrEdit")
	public String toAddOrEdit(Integer id, Model model) {
		List<ProType> proTypes = typeService.queryAll();
		model.addAttribute("proTypes", proTypes);
		if (null != id) {
			FunType funType = funTypeService.findById(id);
			model.addAttribute("funType", funType);
		}
		return "/type/Funedit";
	}

	// 新增或者更新
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(FunType funType, Model model) {
		if (null != funType.getName()) {
			int a = funTypeService.saveOrUpdate(funType);
			if (a > 0) {
				return "redirect:list.do";
			}
		}
		List<ProType> proTypes = typeService.queryAll();
		model.addAttribute("proTypes", proTypes);
		model.addAttribute("funType", funType);
		model.addAttribute("msg", "分类名称不能为空!");
		return "/type/Funedit";

	}

	// 删除单删
	@RequestMapping("/delById")
	public String delById(Integer id) {
		int a = funTypeService.delById(id);
		if (a > 0) {
			return "redirect:list.do";
		}
		return null;
	}

	// 批量删除
	@RequestMapping("/delByIds")
	public String delByIds(Integer[] ids) {
		int a = funTypeService.delByIds(ids);
		if (a > 0) {
			return "redirect:list.do";
		}
		return null;
	}

	// 全部
	@RequestMapping("/AllFunType")
	@ResponseBody
	public List<FunType> AllFunType() {
		List<FunType> funTypes = funTypeService.findAll();
		return funTypes;
	}

	// 根据产品类型搜索
	@RequestMapping("/queryByType")
	@ResponseBody
	public List<FunType> queryByType(Integer typeId) {
		return funTypeService.findByParId(typeId);
	}
}
