package com.lwyykj.core.banner.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwyykj.core.bean.text.Banner;
import com.lwyykj.core.bean.text.BannerPosition;
import com.lwyykj.core.text.service.BannerPositionService;
import com.lwyykj.core.text.service.BannerService;
import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.4.4
 *
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
	@Resource
	private BannerService bannerService;
	@Resource
	private BannerPositionService bannerPositionService;

	// 列表
	@RequestMapping("/list.do")
	public String list(Integer pageNo, String name, Integer positionId, Model model) {
		Pagination pagination = bannerService.selectByPagination(pageNo, name, positionId);
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		model.addAttribute("positionId", positionId);
		return "/banner/list";
	}

	// 去编辑页
	@RequestMapping("/init")
	public String init(Model model, Integer id) {
		List<BannerPosition> bannerPositions = bannerPositionService.queryAll();
		if (null != id) {
			Banner banner = bannerService.selectById(id);
			model.addAttribute("banner", banner);
		}
		model.addAttribute("bannerPositions", bannerPositions);
		return "/banner/edit";
	}

	// 保存更新
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Banner banner, Model model) {
		List<BannerPosition> bannerPositions = bannerPositionService.queryAll();
		if (null != banner.getLinkId()) {
			if (null != banner.getPosition()) {
				if (null != banner.getType()) {
					int result = bannerService.saveOrUpdate(banner);
					if (result > 0) {
						return "redirect:list.do";
					} else {
						model.addAttribute("msg", "提交失败,请联系管理员!");
					}
				} else {
					model.addAttribute("msg", "请选择广告类型,必须准确,否则将无法正常跳转!");
				}
			} else {
				model.addAttribute("msg", "请选择需要展示的位置");
			}
		} else {
			model.addAttribute("msg", "请输入需要链接的通知、产品、医生或者活动的ID,相应各自模块中可以查询!");
		}
		model.addAttribute("bannerPositions", bannerPositions);
		model.addAttribute("banner", banner);
		return "/banner/edit";
	}
	//删除支持批量（假删）,回显未使用
	@RequestMapping("/deletes")
	public String deletes(Integer[] ids,Model model){
		int result = bannerService.delByIds(ids);
		if(result>0){
			return "redirect:list.do";
		}else{
			model.addAttribute("msg", "删除失败!");
		}
		return null;
	}
}
