package com.lwyykj.core.product.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lwyykj.core.bean.factory.Factory;
import com.lwyykj.core.bean.pca.Provinces;
import com.lwyykj.core.factory.service.FactoryService;
import com.lwyykj.core.pca.service.PcaService;
import cn.itcast.common.page.Pagination;

/**
 * 厂家管理
 * 
 * @author yl 2018.3.5
 *
 */
@Controller
@RequestMapping("/factory")
public class FactoryController {

	@Resource
	private FactoryService factoryService;
	@Resource
	private PcaService pcaService;

	// 聊表带分页
	@RequestMapping("/list.do")
	public String list(Integer pageNo, String fac_name, Boolean isShow, String provincesId, String cityId, String area,
			Model model) {
		List<Provinces> provinces = pcaService.findAll();
		model.addAttribute("provinces", provinces);
		Pagination pagination = factoryService.selectByPagination(pageNo, fac_name, isShow, provincesId, cityId, area);
		model.addAttribute("pagination", pagination);
		model.addAttribute("provincesId", provincesId);
		model.addAttribute("cityId", cityId);
		model.addAttribute("area", area);
		model.addAttribute("isShow", isShow);
		return "/factory/list";
	}

	// 进入新增页
	@RequestMapping("/toAdd")
	public String toAdd(Model model,Integer type) {
		List<Provinces> provinces = pcaService.findAll();
		model.addAttribute("provinces", provinces);
		model.addAttribute("type", type);
		return "/factory/edit";
	}

	// 新增或更新
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Factory factory, Model model,Integer type) {
		List<Provinces> provinces = pcaService.findAll();
		model.addAttribute("provinces", provinces);
		if (null != factory.getName()) {
			if (null != factory.getTel()) {
				if (null != factory.getAddress()) {
					if (null != factory.getProvincesId() && null != factory.getCityId() && null != factory.getArea()) {
						int a = factoryService.saveOrUpdate(factory,type);
						if (a > 0) {
							return "redirect:list.do";
						}
					} else {
						model.addAttribute("msg", "地区必须选择!");
						model.addAttribute("fac", factory);
						return "/factory/edit";
					}
				} else {
					model.addAttribute("msg", "地址不能为空！");
					model.addAttribute("fac", factory);
					return "/factory/edit";
				}
			} else {
				model.addAttribute("msg", "联系电话不能为空！");
				model.addAttribute("fac", factory);
				return "/factory/edit";
			}

		} else {
			model.addAttribute("msg", "工厂名字不能为空！");
			model.addAttribute("fac", factory);
			return "/factory/edit";
		}
		model.addAttribute("fac", factory);
		return "/factory/edit";
	}

	// 去编辑页
	@RequestMapping("/toEdit")
	public String toEdit(Integer id, Model model) {
		List<Provinces> provinces = pcaService.findAll();
		model.addAttribute("provinces", provinces);
		Factory factory = factoryService.findById(id);
		model.addAttribute("fac", factory);
		return "/factory/edit";
	}

	// 删除（假删）
	@RequestMapping("/delById")
	public String delById(Integer id) {
		int a = factoryService.delById(id);
		if (a > 0) {
			return "redirect:list.do";
		}
		return null;
	}

	// 批量删除
	@RequestMapping("/delByIds")
	public String delByIds(Integer[] ids) {
		int a = factoryService.delByIds(ids);
		if (a > 0) {
			return "redirect:list.do";
		}
		return null;
	}

	// 批量已审
	@RequestMapping("/upByIds")
	public String upByIds(Integer[] ids) {
		int a = factoryService.upByIDS(ids);
		if (a > 0) {
			return "redirect:list.do";
		}
		return null;
	}

}
