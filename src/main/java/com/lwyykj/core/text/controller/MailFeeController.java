package com.lwyykj.core.text.controller;



import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwyykj.core.bean.pharmacy.Pharmacy;
import com.lwyykj.core.bean.text.MailFee;
import com.lwyykj.core.text.service.MailFeeService;
import com.lwyykj.core.user.service.PharmacyService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/mailfee")
public class MailFeeController {
	@Resource
	private MailFeeService mailFeeService;
	@Resource
	private PharmacyService pharmacyService;
	// 列表
		@RequestMapping("/list.do")
		public String list(String keyword, Integer pageNo, Model model) {
			Pagination pagination = mailFeeService.selectByPagination(keyword, pageNo);
			model.addAttribute("pagination", pagination);
			model.addAttribute("keyword", keyword);
			return "/mailfee/list";
		}
		// 去新增或编辑页
		@RequestMapping("/init")
		public String init(Integer id, Model model) {
			if (null != id) {
				MailFee mailfee = mailFeeService.selectByPrimaryKey(id);
				model.addAttribute("mailfee", mailfee);
				
			}
			model.addAttribute("pharmacies", pharmacyService.findAll());
			return "/mailfee/edit";
		}
		// 提交或更新
		@RequestMapping("/saveOrUpdate")
		public String saveOrUpdate(MailFee mailFee, Model model,HttpServletRequest req) {
		
		
			if (null != mailFee.getTitle()) {
				mailFee.setAddtime((int)(new Date().getTime()/1000));
				int result = mailFeeService.saveOrUpdate(mailFee);
				if (result > 0) {
					return "redirect:list.do";
				}
			} else {
				model.addAttribute("msg", "信息异常！");
			}
			model.addAttribute("mailFee", mailFee);
			return "/mailfee/edit";
		}
		// 批删
		@RequestMapping("/deletes")
		public String deletes(Integer[] ids) {
			int result = mailFeeService.delByIds(ids);
			if (result > 0) {
				return "redirect:list.do";
			}
			return null;
		}
}
