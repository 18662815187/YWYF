package com.lwyykj.core.centerController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理
 * 
 * @author john
 * 
 * 
 */
@Controller
@RequestMapping("/control")
public class CenterController {
	// 此处注入的服务并不在一个tomcat上，所以需要dubbo配合，
	// 而dubbo的注册中心用zookeeper
	// @Resource
	// private TestTbService testTbService;

	// 入口
	/**
	 * ModelAndView :跳转视图+数据 此方法不用 void : 异步时ajax String : 跳转视图 + Model
	 */
	// @RequestMapping("/test/index.do")
	// public String index(Model model) {
	// TestTb testTb = new TestTb();
	// testTb.setName("陈血亏");
	// testTb.setBirthday(new Date());
	// testTbService.insertTestTb(testTb);
	// return "index";
	// }

	// 入口
	@RequestMapping("/index.do")
	public String index(Model model) {
		return "index";
	}

	// 头部
	@RequestMapping("/top.do")
	public String top(Model model) {
		return "top";
	}

	// 身体
	@RequestMapping("/main.do")
	public String main(Model model) {
		return "main";
	}

	// 身体左侧
	@RequestMapping("/left.do")
	public String left(Model model) {
		return "left";
	}

	// 身体右侧
	@RequestMapping("/right.do")
	public String right(Model model) {
		return "right";
	}

	// 商品中心
	@RequestMapping("/frame/product_main.do")
	public String product_main(Model model) {
		return "frame/product_main";
	}

	// 商品中心-左
	@RequestMapping("/frame/product_left.do")
	public String product_left(Model model) {
		return "frame/product_left";
	}

	// 分类中心
	@RequestMapping("/frame/type_main.do")
	public String type_main(Model model) {
		return "frame/type_main";
	}

	// 分类中心-左
	@RequestMapping("/frame/type_left.do")
	public String type_left(Model model) {
		return "frame/type_left";
	}

	// 用户中心-左
	@RequestMapping("/frame/user_left.do")
	public String user_left(Model model) {
		return "frame/user_left";
	}

	// 用户中心
	@RequestMapping("/frame/user_main.do")
	public String user_main(Model model) {
		return "frame/user_main";
	}

	// 广告管理-左
	@RequestMapping("/frame/banner_left.do")
	public String banner_left(Model model) {
		return "frame/banner_left";
	}

	// 广告管理-主体
	@RequestMapping("/frame/banner_main.do")
	public String banner_main(Model model) {
		return "frame/banner_main";
	}

	
}
