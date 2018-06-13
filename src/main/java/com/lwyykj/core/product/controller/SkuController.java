package com.lwyykj.core.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lwyykj.core.bean.product.Sku;
import com.lwyykj.core.product.service.SkuService;

/**
 * 
 * @author yl 2018.3.20 库存sku控制器
 *
 */
@Controller
@RequestMapping("/sku")
public class SkuController {
	@Resource
	private SkuService skuService;

	// 通过产品ID查找sku列表
	@RequestMapping("/list")
	public String list(Integer id, Model model) {
		List<Sku> skus = skuService.findByPid(id);
		model.addAttribute("skus", skus);
		model.addAttribute("pro_id", id);
		return "/sku/list";
	}

	// 删除
	@RequestMapping("delById")
	@ResponseBody
	public Map<String, Object> delById(Integer skuId, Model model) {
		Map<String, Object> map = new HashMap<>();
		Sku sku = skuService.findById(skuId);
		int a = skuService.delBySkuId(skuId);
		if (a > 0) {
			String url = "/sku/list?id=" + sku.getProId();
			map.put("message", "删除成功！");
			map.put("reUrl", url);
			return map;
		} else {
			map.put("message", "删除失败，联系管理员！");
			return map;
		}

	}

	// 到新增页
	@RequestMapping("/toAdd")
	public String toAdd(Integer pro_id,Model model) {
		model.addAttribute("pro_id", pro_id);
		return "/sku/add";
	}

	// 新增提交
	@RequestMapping("/apply")
	public String apply(Sku sku, Model model) {
		model.addAttribute("sku", sku);
		if (null != sku.getSpec()) {
			int a = skuService.insert(sku);
			if (a > 0) {
				model.addAttribute("id", sku.getProId());
				return "redirect:list.do";
			} else {
				model.addAttribute("msg", "新增失败！");
				return "/sku/add";
			}
		} else {
			model.addAttribute("msg", "规格不能为空!");
			return "/sku/add";
		}
	}

	// 更新提交
	@RequestMapping("/upApply")
	@ResponseBody
	public Map<String, Object> upApply(Sku sku) {
		Map<String, Object> map = new HashMap<>();
		int a = skuService.update(sku);
		if (a > 0) {
			map.put("message", "更新成功！");
		}
		return map;
	}

	// 批删
	@RequestMapping("/delByIds")
	@ResponseBody
	public Map<String, Object> delByIds(Integer[] ids) {
		int a = 0;
		Map<String, Object> map = new HashMap<>();
		for (Integer id : ids) {
			a = skuService.delBySkuId(id);
		}
		if (a > 0) {
			map.put("message", "更新成功！");
		}else{
			map.put("message", "更新失败！");
		}
		return map;
	}
}
