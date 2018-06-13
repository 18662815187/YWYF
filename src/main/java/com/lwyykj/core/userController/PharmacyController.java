package com.lwyykj.core.userController;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lwyykj.core.bean.doctor.Hospital;
import com.lwyykj.core.bean.pca.Provinces;
import com.lwyykj.core.bean.pharmacy.Pharmacy;
import com.lwyykj.core.pca.service.PcaService;
import com.lwyykj.core.user.service.HospitalService;
import com.lwyykj.core.user.service.PharmacyService;
import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/pharmacy")
public class PharmacyController {
	@Resource
	private PharmacyService pharmacyService;
	@Resource
	private PcaService pcaService;
	@Resource
	private HospitalService hospitalService;

	// 列表
	@RequestMapping("/list.do")
	public String list(Integer pageNo, String phName, String hosName, String provincesId, String cityId, String area,
			Boolean isShow, Model model) {
		List<Provinces> provinces = pcaService.findAll();
		model.addAttribute("provinces", provinces);
		Pagination pagination = pharmacyService.selectByPagination(pageNo, hosName, phName, provincesId, cityId, area,
				isShow);
		model.addAttribute("pagination", pagination);
		model.addAttribute("phName", phName);
		model.addAttribute("hosName", hosName);
		model.addAttribute("provincesId", provincesId);
		model.addAttribute("cityId", cityId);
		model.addAttribute("area", area);
		model.addAttribute("isShow", isShow);
		return "/pharmacy/list";
	}

	// 去更新或者新增页面
	@RequestMapping("/toAddOrEdit")
	public String toAddOrEdit(Integer id, Integer editType, Model model) {
		List<Pharmacy> pharmacies = pharmacyService.findAll();
		model.addAttribute("pharmacies", pharmacies);
		List<Hospital> hospitals = hospitalService.findAll();
		model.addAttribute("hospitals", hospitals);
		List<Provinces> provinces = pcaService.findAll();
		model.addAttribute("provinces", provinces);
		if (null != id) {
			Pharmacy pharmacy = pharmacyService.findById(id);
			model.addAttribute("pharmacy", pharmacy);
		}
		return "/pharmacy/edit";
	}

	// 更新或保存
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Pharmacy pharmacy, Integer editType, Model model) {
		List<Pharmacy> pharmacies = pharmacyService.findAll();
		model.addAttribute("pharmacies", pharmacies);
		List<Hospital> hospitals = hospitalService.findAll();
		model.addAttribute("hospitals", hospitals);
		List<Provinces> provinces = pcaService.findAll();
		model.addAttribute("provinces", provinces);
		if (null != pharmacy.getPharmacyName()) {
			if (null != pharmacy.getPwd()) {
				if (null != pharmacy.getHosId()) {
					if (null != pharmacy.getType()) {
						if (null != pharmacy.getAddress()) {
							if (null != pharmacy.getCellPhone() && null != pharmacy.getTel()) {
								int a = pharmacyService.saveOrUpdate(pharmacy, editType);
								if (a > 0) {
									return "redirect:list.do";
								} else {
									model.addAttribute("msg", "更新失败，请联系管理员！");
									model.addAttribute("pharmacy", pharmacy);
									return "/pharmacy/edit";
								}
							} else {
								model.addAttribute("msg", "登陆手机或固定电话不能为空！");
								model.addAttribute("pharmacy", pharmacy);
								return "/pharmacy/edit";
							}
						} else {
							model.addAttribute("msg", "地址不能为空！");
							model.addAttribute("pharmacy", pharmacy);
							return "/pharmacy/edit";
						}
					} else {
						model.addAttribute("msg", "药房类型！");
						model.addAttribute("pharmacy", pharmacy);
						return "/pharmacy/edit";
					}
				} else {
					model.addAttribute("msg", "所属范围医院ID不能为空！");
					model.addAttribute("pharmacy", pharmacy);
					return "/pharmacy/edit";
				}
			} else {
				model.addAttribute("msg", "密码不能为空！");
				model.addAttribute("pharmacy", pharmacy);
				return "/pharmacy/edit";
			}
		} else {
			model.addAttribute("msg", "药店名称不能为空！");
			model.addAttribute("pharmacy", pharmacy);
			return "/pharmacy/edit";
		}
	}

	// 批量已审
	@RequestMapping("/upByIds")
	public String upByIds(Integer[] ids,Model model) {
		int a = pharmacyService.upByIds(ids);
		if (a > 0) {
			return "redirect:list.do";
		}else{
			model.addAttribute("msg", "更新失败，请联系管理员！");
			return null;
		}
	}
	//批量删除
	@RequestMapping("/delByIds")
	public String delByIds(Integer[] ids,Model model){
		int a = pharmacyService.delByIds(ids);
		if (a > 0) {
			return "redirect:list.do";
		}else{
			model.addAttribute("msg", "更新失败，请联系管理员！");
			return null;
		}
	}
}
