package com.lwyykj.core.banner.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwyykj.core.bean.text.BannerPosition;
import com.lwyykj.core.text.service.BannerPositionService;

import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.4.4
 *
 */
@Controller
@RequestMapping("/posstion")
public class BannerPositionController {
	@Resource
	private BannerPositionService bannerPositionService;

	// 列表
	@RequestMapping("/list.do")
	public String selectByPagination(Integer pageNo, String name, Model model) {
		Pagination pagination = bannerPositionService.selectByPagination(pageNo, name);
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		return "/banner/position_list";
	}

	// 去添加页面
	@RequestMapping("/toEdit")
	public String toEdit(Model model, Integer id) {
		if (null != id) {
			BannerPosition bannerPosition = bannerPositionService.findById(id);
			model.addAttribute("bannerPosition", bannerPosition);
		}
		return "/banner/PosEdit";
	}

	// 保存更新
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(BannerPosition bannerPosition, Model model) {
		if (null != bannerPosition.getPosition()) {
			int result = bannerPositionService.saveOrUpdate(bannerPosition);
			if (result > 0) {
				return "redirect:list.do";
			} else {
				model.addAttribute("msg", "更新失败，请联系管理员!");
			}
		} else {
			model.addAttribute("msg", "位置不能为空！");
		}
		model.addAttribute("bannerPosition", bannerPosition);
		return "/banner/PosEdit";

	}

	// 删除支持批量,model返回提示为预留，本次并未真正使用
	@RequestMapping("/deletes")
	public String deletes(Integer[] ids, Model model) {
		if (null != ids) {
			bannerPositionService.delByIds(ids);
		} else {
			model.addAttribute("msg", "id不能为空!");
		}
		return "redirect:list.do";

	}
}
