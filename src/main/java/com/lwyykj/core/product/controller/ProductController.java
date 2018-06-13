package com.lwyykj.core.product.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lwyykj.core.bean.factory.Factory;
import com.lwyykj.core.bean.pharmacy.Pharmacy;
import com.lwyykj.core.bean.product.Brand;
import com.lwyykj.core.bean.product.Product;
import com.lwyykj.core.bean.type.ProType;
import com.lwyykj.core.factory.service.FactoryService;
import com.lwyykj.core.product.service.BrandService;
import com.lwyykj.core.product.service.ProductService;
import com.lwyykj.core.type.service.DiseaseTypeService;
import com.lwyykj.core.type.service.FunTypeService;
import com.lwyykj.core.type.service.TypeService;
import com.lwyykj.core.user.service.PharmacyService;
import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Resource
	private ProductService productService;
	@Resource
	private BrandService brandService;
	@Resource
	private FactoryService factoryService;
	@Resource
	private PharmacyService pharmacyService;
	@Resource
	private TypeService typeService;
	@Resource
	private FunTypeService funTypeService;
	@Resource
	private DiseaseTypeService diseaseTypeService;

	// 列表
	@RequestMapping("/list.do")
	public String selectByPagination(Integer pageNo, String pro_name, String ph_name, Boolean isShow, Model model) {
		Pagination pagination = productService.selectAllProduct(pageNo, pro_name, ph_name, isShow);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pro_name", pro_name);
		model.addAttribute("ph_name", ph_name);
		model.addAttribute("isShow", isShow);
		return "product/list";
	}

	// 去修改页面
	// @RequestMapping("/toEdit/{id}")
	@RequestMapping("/toEdit")
	public String toEdit(Integer id, Model model,Integer type) {
		List<ProType> types = typeService.queryAll();
		List<Brand> brands = brandService.findAll();
		List<Factory> factories = factoryService.findAll();
		Product product = productService.selectById(id);
		List<Pharmacy> pharmacies = pharmacyService.findAll();
		model.addAttribute("types", types);
		model.addAttribute("pharmacies", pharmacies);
		model.addAttribute("brands", brands);
		model.addAttribute("factories", factories);
		model.addAttribute("pro", product);
		model.addAttribute("type", type);
		return "product/add";
	}

	// 去添加页面
	@RequestMapping("/toAdd.do")
	public String toAdd(Model model,Integer type) {
		List<Brand> brands = brandService.findAll();
		List<Factory> factories = factoryService.findAll();
		List<Pharmacy> pharmacies = pharmacyService.findAll();
		List<ProType> types = typeService.queryAll();
		model.addAttribute("types", types);
		model.addAttribute("pharmacies", pharmacies);
		model.addAttribute("brands", brands);
		model.addAttribute("factories", factories);
		model.addAttribute("type", type);
		return "product/add";
	}

	// 保存或者修改
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Product product, Model model) {
		List<Brand> brands = brandService.findAll();
		List<Factory> factories = factoryService.findAll();
		List<Pharmacy> pharmacies = pharmacyService.findAll();
		List<ProType> types = typeService.queryAll();
		model.addAttribute("types", types);
		model.addAttribute("pharmacies", pharmacies);
		model.addAttribute("brands", brands);
		model.addAttribute("factories", factories);
		model.addAttribute("pro", product);
		if (null != product.getName()) {
			if (null != product.getSpecs()) {
				if (null != product.getFacId()) {
					if (null != product.getBrandId()) {
						if (null != product.getProTypeid()&&null!=product.getFunId()&&null!=product.getDiseaseId()) {
							int a = productService.saveOrUpdate(product);
							if (a > 0) {
								return "redirect:list.do";
							} else {
								model.addAttribute("msg", "更新失败，请联系管理员!");
								return "product/add";
							}
						} else {
							model.addAttribute("msg", "类型必须选择！");
							return "product/add";
						}
					} else {
						model.addAttribute("msg", "品牌不能为空！");
						return "product/add";
					}
				} else {
					model.addAttribute("msg", "所属厂家不能为空！");
					return "product/add";
				}
			} else {
				model.addAttribute("msg", "规格不能为空！");
				return "product/add";
			}
		} else {
			model.addAttribute("msg", "产品名称不能为空！");
			return "product/add";
		}
	}

	// 批删
	@RequestMapping("/delByIds")
	public String delByIds(Integer[] ids, Model model) {
		int a = productService.delByIds(ids);
		if (a > 0) {
			return "redirect:list.do";
		} else {
			model.addAttribute("msg", "更新失败！");
			return null;
		}
	}

	// 批量上架
	@RequestMapping("/isShowByIds")
	public String isShowByIds(Integer[] ids, Model model) {
		int a = productService.upByIds(ids);
		if (a > 0) {
			return "redirect:list.do";
		} else {
			model.addAttribute("msg", "更新失败！");
			return null;
		}
	}

	// 批量下架
	@RequestMapping("/unShowByIds")
	public String unShowByIds(Integer[] ids, Model model) {
		int a = productService.unShow(ids);
		if (a > 0) {
			return "redirect:list.do";
		} else {
			model.addAttribute("msg", "更新失败！");
			return null;
		}
	}

}
