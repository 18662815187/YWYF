package com.lwyykj.core.userController;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwyykj.core.bean.doctor.Doctor;
import com.lwyykj.core.bean.doctor.Hospital;
import com.lwyykj.core.bean.pca.Provinces;
import com.lwyykj.core.pca.service.PcaService;
import com.lwyykj.core.user.service.DoctorService;
import com.lwyykj.core.user.service.HospitalService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	@Resource
	private DoctorService doctorService;
	@Resource
	private PcaService pcaService;
	@Resource
	private HospitalService hospitalService;

	// 列表带分页
	@RequestMapping("/list.do")
	public String list(Integer pageNo, String docName, String hosName, String provincesId, String cityId, String area,
			Boolean isShow, Model model) {
		List<Provinces> provinces = pcaService.findAll();
		model.addAttribute("provinces", provinces);
		Pagination pagination = doctorService.selectByPagination(pageNo, docName, hosName, provincesId, cityId, area,
				isShow);
		model.addAttribute("pagination", pagination);
		model.addAttribute("hosName", hosName);
		model.addAttribute("docName", docName);
		model.addAttribute("provincesId", provincesId);
		model.addAttribute("cityId", cityId);
		model.addAttribute("area", area);
		return "/doctor/list";
	}

	// 新增或编辑提交
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Doctor doctor, Integer editType, Model model) {
		List<Hospital> hospitals = hospitalService.findAll();
		model.addAttribute("hospitals", hospitals);
		List<Doctor> doctors = doctorService.findAll();
		model.addAttribute("doctors", doctors);
		model.addAttribute("editType", editType);
		model.addAttribute("doctor", doctor);
		if (null != doctor.getName()) {
			if (null != doctor.getPwd()) {
				if (null != doctor.getHosId()) {
					if (null != doctor.getSex()) {
						if (null != doctor.getTitle()) {
							int a = doctorService.saveOrUpdate(doctor, editType);
							if (a > 0) {
								return "redirect:list.do";
							} else {
								model.addAttribute("msg", "更新失败，请联系管理员！");
								return "/doctor/edit";
							}
						} else {
							model.addAttribute("msg", "职称不能为空！");
							return "/doctor/edit";
						}
					} else {
						model.addAttribute("msg", "性别不能为空！");
						return "/doctor/edit";
					}
				} else {
					model.addAttribute("msg", "所属医院不能为空!");
					return "/doctor/edit";
				}
			} else {
				model.addAttribute("msg", "密码不能为空！");
				return "/doctor/edit";
			}
		} else {
			model.addAttribute("msg", "名字不能为空！");
			return "/doctor/edit";
		}
	}

	// 去新增页或编辑页
	@RequestMapping("/toSaveOrEdit")
	public String toSaveOrEdit(Integer id, Integer editType, Model model) {
		List<Hospital> hospitals = hospitalService.findAll();
		model.addAttribute("hospitals", hospitals);
		List<Doctor> doctors = doctorService.findAll();
		model.addAttribute("doctors", doctors);
		model.addAttribute("editType", editType);
		if (null != id) {
			Doctor doctor = doctorService.findById(id);
			model.addAttribute("doctor", doctor);
			return "/doctor/edit";
		} else {
			return "/doctor/edit";
		}
	}

	// 批量删除
	@RequestMapping("/delByIds")
	public String delByIds(Integer[] ids, Model model) {
		int a = doctorService.delByIds(ids);
		if (a > 0) {
			return "redirect:list.do";
		} else {
			model.addAttribute("msg", "更新失败请联系管理员！");
			return null;
		}
	}

	// 批量已审
	@RequestMapping("/upByIds")
	public String upByIds(Model model, Integer[] ids) {
		int a = doctorService.upByIds(ids);
		if (a > 0) {
			return "redirect:list.do";
		} else {
			model.addAttribute("msg", "更新失败请联系管理员！");
			return null;
		}
	}

}
