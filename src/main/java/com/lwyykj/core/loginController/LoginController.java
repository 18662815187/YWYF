package com.lwyykj.core.loginController;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttributes;
import com.lwyykj.common.Constants;
import com.lwyykj.common.EncodePassword;
import com.lwyykj.core.bean.record.AdminLoginRecord;
import com.lwyykj.core.bean.text.Notice;
import com.lwyykj.core.bean.user.Admin;
import com.lwyykj.core.login.service.AdminService;
import com.lwyykj.core.loginRecord.service.AdminLoginRecordService;
import com.lwyykj.core.text.service.NoticeService;
import com.lwyykj.core.vercode.service.VercodeService;

@Controller
// @SessionAttributes(Constants.USER_NAME)
public class LoginController {
	@Resource
	private AdminService adminService;
	@Resource
	private VercodeService vercodeService;
	@Resource
	private AdminLoginRecordService adminLoginRecordService;
	@Resource
	private NoticeService noticeService;

	// 判断用户是否登录,用于页面头部登录、注册、退出按钮的显示与隐藏
	@ResponseBody
	@RequestMapping("isLogin.aspx")
	public MappingJacksonValue isLogin(String callback, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		Integer result = 0;
		// 判断用户是否已登录
		// HttpSession session = request.getSession();
		Object username = session.getAttribute(Constants.USER_NAME);
		if (null != username) {
			result = 1;
		}
		// 支持jsonp,回调
		MappingJacksonValue mjv = new MappingJacksonValue(result);
		mjv.setJsonpFunction(callback);
		return mjv;
	}

	// 提交登录POST,使用springmvc的Converter转换器可以大大简化判断空值的操作,转换器工厂在mvc配置文件，工具类在common项目中
	@RequestMapping("/login")
	public String login(String userName, String password, String verifyCode, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String code = null;
		HttpSession session = request.getSession();
		// 如有验证码优先级在最前面
		if (null != verifyCode) {
			// 1.用户名不能为空
			if (null != userName) {
				// 2.密码不能为空
				if (null != password) {
					// 验证码是否正确
					// 1:获取cookie里面的验证码信息
					Cookie[] cookies = request.getCookies();
					if (cookies != null && cookies.length > 0) {
						for (Cookie cookie : cookies) {
							if ("imagecode".equals(cookie.getName())) {
								code = cookie.getValue();
								// System.out.println("***************这是COOKIE的："
								// + code);
								break;
							}
						}
					} else {
						// 1:获取session验证码的信息
						String key = (String) request.getSession().getAttribute("sessionkey");
						code = (String) request.getSession().getAttribute(key);
						// System.out.println("***************这是session的：" +
						// code);
					}
					if (!StringUtils.isEmpty(verifyCode) && verifyCode.equals(code)) {
						// 3.用户名必须正确
						Admin admin = adminService.findByTel(userName);
						if (null != admin) {
							// 4.密码必须正确
							if (admin.getPassWord().equals(EncodePassword.encodePassword(password))) {
								// 5.保存用户名到session，原名JSESSIONID，自定义为CSESSIONID，
								// 避免被request使用造成在集群中其他机器重新生成而取不到数据，生成
								// 规则为UUID（36位带“-”连接），把4个“-”去掉剩余32位
								// 首次使用session需要创建后保存到cookie，非首次直接在cookie取
								session.setAttribute(Constants.USER_NAME, admin.getUserName());
								session.setMaxInactiveInterval(60 * 30);
								// model.addAttribute("pharmacy", pharmacy);
								session.setAttribute("isShow", admin.getIsShow());
								session.setAttribute("pid", admin.getId());
								// session.setAttribute("money",
								// admin.getMoney());
								// request.getSession().setAttribute("pid",
								// pharmacy.getId());
								List<Notice> notices = noticeService.findAll();
								session.setAttribute("notices", notices);
								int id = admin.getId();
								AdminLoginRecord loginRecord = adminLoginRecordService.findById(id);
								if (loginRecord != null) {
									session.setAttribute(Constants.LOGIN_STATUS, true);
									session.setAttribute(Constants.LOGIN_TIME, loginRecord.getLoginTime());
									// System.out.println(loginRecord.getLoginTime());
									AdminLoginRecord loginRecord2 = new AdminLoginRecord();
									loginRecord2.setUid(id);
									loginRecord2.setLoginTime(new Date());
									adminLoginRecordService.insertLoginRecord(loginRecord2);
								} else {
									session.setAttribute(Constants.LOGIN_STATUS, false);
									AdminLoginRecord loginRecord2 = new AdminLoginRecord();
									loginRecord2.setUid(id);
									loginRecord2.setLoginTime(new Date());
									adminLoginRecordService.insertLoginRecord(loginRecord2);
								}
								// 6.跳转到系统首页
								return "forward:/control/index.do";
								// return "index";
							} else {
								model.addAttribute("userName", userName);
								model.addAttribute("error", "密码不正确");
							}
						} else {
							model.addAttribute("userName", userName);
							model.addAttribute("error", "没有该用户！");
						}
					} else {
						model.addAttribute("userName", userName);
						model.addAttribute("error", "验证码错误！");
					}
				} else {
					model.addAttribute("userName", userName);
					model.addAttribute("error", "密码不能为空！");
				}
			} else {
				model.addAttribute("userName", userName);
				model.addAttribute("error", "用户名不能为空！");
			}
		} else {
			model.addAttribute("userName", userName);
			model.addAttribute("error", "验证码不能为空！");
		}
		return "../../login";
	}

	// 去登录页
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "../../login";
	}

	// 退出登录
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:toLogin";
	}

	// 推出登陆2
	@RequestMapping("/logout2.do")
	public String logout2(HttpSession session) {
		session.invalidate();
		return "../../login";
	}

	// 重置密码
//	@RequestMapping("/czPwd")
//	@ResponseBody
//	public Object czPwd(String tel, String code, String password, String pwd) {
//		Map<String, Object> map = new HashMap<>();
//		if (null != tel) {
//			if (null != code) {
//				if (null != password && null != pwd) {
//					Vercode vercode = vercodeService.selectByTel(tel);
//					if (null != vercode) {
//						if (code.equals(vercode.getCode())) {
//							if (password.equals(pwd)) {
//								Pharmacy pharmacy = pharmacyService.selectByTel(tel);
//								if (null != pharmacy) {
//									pharmacy.setPassword(EncodePassword.encodePassword(password));
//									int result = pharmacyService.updateByTel(tel, pharmacy);
//									if (result > 0) {
//										System.out.println("修改成功！");
//										verCodeService.delByTel(tel);
//										map.put("status", 1);
//									} else {
//										System.out.println("修改失败！");
//										map.put("status", 0);
//										map.put("error", "修改失败，请重试！");
//									}
//								} else {
//									map.put("status", 0);
//									map.put("error", "此手机号并未注册，请先注册！");
//									verCodeService.delByTel(tel);
//								}
//							} else {
//								map.put("status", 0);
//								map.put("error", "两次输入的密码不一致！");
//							}
//
//						} else {
//							map.put("status", 0);
//							map.put("error", "验证码输入不正确，请检查后再次输入！");
//						}
//
//					} else {
//						map.put("status", 0);
//						map.put("error", "非法验证码，请点击获取验证码按钮重新获取！");
//					}
//
//				} else {
//					map.put("status", 0);
//					map.put("error", "密码或重复密码不能为空!");
//				}
//			} else {
//				map.put("status", 0);
//				map.put("error", "验证码不能为空！");
//			}
//		} else {
//			map.put("status", 0);
//			map.put("error", "手机号不能为空！");
//		}
//		return map;
//	}

	// 去重置页面
	@RequestMapping("/toCzPwd")
	public String toCzPwd() {
		return "Cz_pwd";
	}

}
