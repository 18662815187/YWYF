package com.lwyykj.core.type.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lwyykj.core.bean.type.Disease;
import com.lwyykj.core.bean.type.FunType;
import com.lwyykj.core.type.service.DiseaseTypeService;
import com.lwyykj.core.type.service.FunTypeService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/DisType")
public class DiseaseTypeController {
	@Resource
	private DiseaseTypeService diseaseTypeService;
	@Resource
	private FunTypeService funTypeService;

	// 列表带分页
	@RequestMapping("/list.do")
	public String selectByPagination(Integer pageNo, String dis_name, Integer funTypeId, Model model) {
		List<FunType> funTypes = funTypeService.findAll();
		Pagination pagination = diseaseTypeService.selectByPagination(pageNo, dis_name, funTypeId);
		model.addAttribute("pagination", pagination);
		model.addAttribute("dis_name", dis_name);
		model.addAttribute("funTypeId", funTypeId);
		model.addAttribute("funTypes", funTypes);
		return "/type/diseaseList";
	}

	// 去新增页或编辑页
	@RequestMapping("/toAddOrEdit")
	public String toAddOrEdit(Integer id, Model model) {
		List<FunType> funTypes = funTypeService.findAll();
		model.addAttribute("funTypes", funTypes);
		if (null != id) {
			Disease disease = diseaseTypeService.findById(id);
			model.addAttribute("disease", disease);
		}
		return "/type/diseaseEdit";
	}

	// 保存编辑提交
	@RequestMapping("/apply")
	public String apply(Disease disease, Model model) {
		if (null != disease.getName()) {
			int a = diseaseTypeService.saveOrUpdate(disease);
			if (a > 0) {
				return "redirect:list.do";
			}
		}
		model.addAttribute("disease", disease);
		model.addAttribute("msg", "请完善资料！");
		return "/type/diseaseEdit";
	}

	// 删除
	@RequestMapping("/delById")
	public String delById(Integer id) {
		int a = diseaseTypeService.delById(id);
		if (a > 0) {
			return "redirect:list.do";
		}
		return null;
	}

	// 批量删除
	@RequestMapping("/delByIds")
	public String delByIds(Integer[] ids) {
		int a = diseaseTypeService.delByIds(ids);
		if (a > 0) {
			return "redirect:list.do";
		}
		return null;
	}

	// 全部
	@RequestMapping("/AllDeas")
	@ResponseBody
	public List<Disease> AllDeas() {
		List<Disease> diseases = diseaseTypeService.queryAll();
		return diseases;
	}

	// 根据人体系统分类ID查询所有疾病
	@RequestMapping("/queryByFid")
	@ResponseBody
	public List<Disease> queryByFid(Integer fid) {
		return diseaseTypeService.findByFid(fid);
	}

}
