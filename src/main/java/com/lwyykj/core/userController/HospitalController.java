package com.lwyykj.core.userController;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lwyykj.core.bean.doctor.Hospital;
import com.lwyykj.core.bean.pca.Provinces;
import com.lwyykj.core.pca.service.PcaService;
import com.lwyykj.core.user.service.HospitalService;
import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.3.14
 *
 */

@Controller
@RequestMapping("/hospital")
public class HospitalController {
	@Resource
	private HospitalService hospitalService;
	@Resource
	private PcaService pcaService;

	// 列表
	@RequestMapping("/list.do")
	public String selectByPagination(Integer pageNo, String hosName, String provincesId, String cityId, String area,
			Boolean isShow,Model model) {
		List<Provinces> provinces = pcaService.findAll();
		model.addAttribute("provinces", provinces);
		Pagination pagination = hospitalService.selectByPagination(pageNo, hosName, provincesId, cityId, area,isShow);
		model.addAttribute("pagination", pagination);
		model.addAttribute("hosName", hosName);
		model.addAttribute("provincesId", provincesId);
		model.addAttribute("cityId", cityId);
		model.addAttribute("area", area);
		return "/hospital/list";
	}

	// 去新增或更新页面
	@RequestMapping("/toAddOrEdit")
	public String toAddOrEdit(Integer id, Integer type, Model model) {
		List<Provinces> provinces = pcaService.findAll();
		model.addAttribute("provinces", provinces);
		model.addAttribute("type", type);
		if (null != id) {
			Hospital hospital = hospitalService.findById(id);
			model.addAttribute("hospital", hospital);
		}
		return "/hospital/edit";
	}

	// 提交
	@RequestMapping("/apply")
	public String apply(Hospital hospital, Integer type, Model model) {
		List<Provinces> provinces = pcaService.findAll();
		if (null != hospital.getHosName()) {
			if (null != hospital.getTel()) {
				if (null != hospital.getPwd()) {
					int a = hospitalService.saveOrUpdate(hospital, type);
					if (a > 0) {
						return "redirect:list.do";
					} else {
						model.addAttribute("provinces", provinces);
						model.addAttribute("hospital", hospital);
						model.addAttribute("type", type);
						model.addAttribute("msg", "更新失败，请联系管理员！");
						return "/hospital/edit";
					}
				} else {
					model.addAttribute("provinces", provinces);
					model.addAttribute("hospital", hospital);
					model.addAttribute("type", type);
					model.addAttribute("msg", "密码不能为空");
					return "/hospital/edit";
				}
			} else {
				model.addAttribute("provinces", provinces);
				model.addAttribute("hospital", hospital);
				model.addAttribute("type", type);
				model.addAttribute("msg", "电话号码不能为空！");
				return "/hospital/edit";
			}
		} else {
			model.addAttribute("provinces", provinces);
			model.addAttribute("hospital", hospital);
			model.addAttribute("type", type);
			model.addAttribute("msg", "医院名称不能为空！");
			return "/hospital/edit";
		}
	}

	// 批量删除
	@RequestMapping("/delByIds")
	public String delByIds(Integer[] ids,Model model) {
		if (null != ids) {
			int a = hospitalService.delByIds(ids);
			if (a > 0) {
				return "redirect:list.do";
			} else {
				model.addAttribute("msg", "修改失败，请重试！");
				return null;
			}
		} else {
			model.addAttribute("msg", "id不能为空！");
			return null;
		}
	}
	//批量审核
	@RequestMapping("/show")
	public String show(Integer[] ids,Model model){
		if (null != ids) {
			int a = hospitalService.upByIds(ids);
			if (a > 0) {
				return "redirect:list.do";
			} else {
				model.addAttribute("msg", "修改失败，请重试！");
				return null;
			}
		} else {
			model.addAttribute("msg", "id不能为空！");
			return null;
		}
	}
}
