package com.lwyykj.core.userController;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lwyykj.core.bean.user.User;
import com.lwyykj.core.user.service.UserService;
import cn.itcast.common.page.Pagination;

/**
 * 
 * @author yl 2018.3.13
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	// 列表
	@RequestMapping("/list.do")
	public String selectByPagination(Integer pageNo, String name, String tel, Integer status, Model model) {
		Pagination pagination = userService.selectByPagination(pageNo, name, tel, status);
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		model.addAttribute("tel", tel);
		return "/user/list";
	}

	// 去新增或修改页面
	@RequestMapping("/toAddOrEdit")
	public String toAddOrEdit(Integer id, Integer type, Model model) {
		if (null != id) {
			User user = userService.findById(id);
			model.addAttribute("user", user);
			return "/user/add";
		} else {
			model.addAttribute("type", type);
			return "/user/add";
		}
	}

	// 提交新增或修改
	@RequestMapping("/apply")
	public String apply(User user, Integer type, Model model) {
		if (null != user.getId() && user.getId() > 0) {
			Integer a = userService.saveOrUpdate(user, type);
			if (a > 0) {
				return "redirect:list.do";
			} else {
				model.addAttribute("user", user);
				model.addAttribute("msg", "更新失败，请联系管理员！");
				return "/user/edit";
			}
		} else {
			if (null != user.getNickname()) {
				if (null != user.getTel()) {
					if (null != user.getPwd()) {
						Integer a = userService.saveOrUpdate(user, type);
						if (a > 0) {
							return "redirect:list.do";
						} else {
							model.addAttribute("user", user);
							model.addAttribute("msg", "添加失败，请联系管理员！");
							return "/user/edit";
						}
					} else {
						model.addAttribute("user", user);
						model.addAttribute("msg", "密码不能为空！");
						return "/user/edit";
					}
				} else {
					model.addAttribute("user", user);
					model.addAttribute("msg", "手机号码不能为空！");
					return "/user/edit";
				}
			} else {
				model.addAttribute("user", user);
				model.addAttribute("msg", "昵称不能为空！");
				return "/user/edit";
			}
		}
	}

	// 冻结,return null是预留
	@RequestMapping("/freeze")
	public String freeze(Integer id, Model model) {
		if (null != id) {
			int a = userService.delById(id);
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

	// 批量冻结 return null是预留
	@RequestMapping("/freezes")
	public String freezes(Integer[] ids, Model model) {
		if (null != ids) {
			int a = userService.delByIds(ids);
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

	// 批量已审 return null是预留
	@RequestMapping("/auditByIds")
	public String auditByIds(Integer[] ids,Model model) {
		if (null != ids) {
			int a = userService.auditByIds(ids);
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
	//批量屏蔽 return null是预留
	@RequestMapping("/shieldByIds")
	public String shieldByIds(Integer[] ids,Model model){
		if (null != ids) {
			int a = userService.shieldByIds(ids);
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
