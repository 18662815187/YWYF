package com.lwyykj.core.product.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwyykj.core.bean.factory.Factory;
import com.lwyykj.core.bean.product.Brand;
import com.lwyykj.core.factory.service.FactoryService;
import com.lwyykj.core.product.service.BrandService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/brand")
public class BrandController {
	@Resource
	private BrandService brandService;
	@Resource
	private FactoryService factoryService;

	// 列表带分页
	@RequestMapping("/list.do")
	public String list(String Brand_name, String fac_name, Integer pageNo, Model model, Boolean isShow) {
		Pagination pagination = brandService.selectByPagination(Brand_name, pageNo, fac_name, isShow);
		model.addAttribute("pagination", pagination);
		model.addAttribute("Brand_name", Brand_name);
		model.addAttribute("fac_name", fac_name);
		model.addAttribute("isShow", isShow);
		return "/brand/list";
	}

	// 去新增页面
	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		List<Factory> factories = factoryService.findAll();
		model.addAttribute("factories", factories);
		return "/brand/add";
	}

	// 新增或更新
	@RequestMapping("/add.do")
	public String add(Brand brand, Model model) {
		List<Factory> factories = factoryService.findAll();
		model.addAttribute("factories", factories);
		if (null != brand.getBrandName() && null != brand.getFacId()) {
			int a = brandService.saveOrUpdate(brand);
			if (a > 0) {
				return "forward:list.do";
			}
		}
		model.addAttribute("brand", brand);
		model.addAttribute("msg", "注意：品牌名称和所属厂家不能为空！");
		return "/brand/add";
	}

	// 去更新页面
	@RequestMapping("/toEdit")
	public String toEdit(Integer id, Integer pageNo, String Brand_name, String fac_name, Boolean isShow, Model model) {
		List<Factory> factories = factoryService.findAll();
		model.addAttribute("factories", factories);
		Brand brand = brandService.findById(id);
		model.addAttribute("brand", brand);
		return "/brand/add";
	}

	// 删除
	@RequestMapping("/delete.do")
	public String delById(Integer id) {
		brandService.delById(id);
		return "forward:list.do";
	}

	// 批量删除
	@RequestMapping("/deletes.php")
	public String delByIds(Integer[] ids) {
		brandService.delByIds(ids);
		return "forward:list.do";
	}

	// 批量已审
	@RequestMapping("/upByIds")
	public String upByIds(Integer[] ids) {
		int a = brandService.upByIds(ids);
		if (a > 0) {
			return "forward:list.do";
		}
		return null;
	}
}
