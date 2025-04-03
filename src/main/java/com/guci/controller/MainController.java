package com.guci.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

//	@RequestMapping(value = "/", method={RequestMethod.GET, RequestMethod.POST})
//	public String index() {
//		return "index";
//	}

//	@RequestMapping(value="/newProduct", method={RequestMethod.GET, RequestMethod.POST})
//	public String newProduct() {
//		return "newProduct";
//	}

//	@RequestMapping(value="/bestProduct", method={RequestMethod.GET, RequestMethod.POST})
//	public String bestProduct() {
//		return "bestProduct";
//	}

//	@RequestMapping(value="/manProduct", method={RequestMethod.GET, RequestMethod.POST})
//	public String manProduct() {
//		return "manProduct";
//	}

//	@RequestMapping(value="/womanProduct", method={RequestMethod.GET, RequestMethod.POST})
//	public String womanProduct() {
//		return "womanProduct";
//	}

//	@RequestMapping(value="/unisexProduct", method={RequestMethod.GET, RequestMethod.POST})
//	public String unisexProduct() {
//		return "unisexProduct";
//	}

//	@RequestMapping(value="/notice", method={RequestMethod.GET, RequestMethod.POST})
//	public String notice() {
//		return "notice";
//	}

//	@RequestMapping(value="/faq", method={RequestMethod.GET, RequestMethod.POST})
//	public String faq() {
//		return "faq";
//	}

//	@RequestMapping(value="/question", method={RequestMethod.GET, RequestMethod.POST})
//	public String question() {
//		return "question";
//	}

//	@RequestMapping(value="/ques_register", method={RequestMethod.GET, RequestMethod.POST})
//	public String ques_register() {
//		return "ques_register";
//	}

//	@RequestMapping(value="/cart", method={RequestMethod.GET, RequestMethod.POST})
//	public String cart() {
//		return "/cart";
//	}

	@RequestMapping(value="/checkout", method={RequestMethod.GET, RequestMethod.POST})
	public String checkout() {
		return "/checkout";
	}

//	@RequestMapping(value="/mypage", method={RequestMethod.GET, RequestMethod.POST})
//	public String mypage() {
//		return "/mypage";
//	}

//	@RequestMapping(value="/mypageCheckPw", method={RequestMethod.GET, RequestMethod.POST})
//	public String mypageCheckPw() {
//		return "/mypageCheckPw";
//	}

	@RequestMapping(value="/orderlist", method={RequestMethod.GET, RequestMethod.POST})
	public String orderlist() {
		return "/orderlist";
	}

	@RequestMapping(value="/mypage_cart", method={RequestMethod.GET, RequestMethod.POST})
	public String mypage_cart() {
		return "/mypage_cart";
	}

//	@RequestMapping(value="/update_info", method= {RequestMethod.GET, RequestMethod.POST})
//	public String update_info() {
//		return "/update_info";
//	}

	@RequestMapping(value="/exchange", method= {RequestMethod.GET, RequestMethod.POST})
	public String exchange() {
		return "/exchange";
	}

	@RequestMapping(value="/questionList", method= {RequestMethod.GET, RequestMethod.POST})
	public String questionList() {
		return "/questionList";
	}

//	@RequestMapping(value="/product_detail", method= {RequestMethod.GET, RequestMethod.POST})
//	public String product_detail() {
//		return "/product_detail";
//	}

//	@RequestMapping(value="/account", method= {RequestMethod.GET, RequestMethod.POST})
//	public String account() {
//		return "/account";
//	}

//	@RequestMapping(value="/delete_info", method= {RequestMethod.GET, RequestMethod.POST})
//	public String delete_info() {
//		return "/delete_info";
//	}

//	@RequestMapping(value="/search_product", method= {RequestMethod.GET, RequestMethod.POST})
//	public String search_product() {
//		return "/search_product";
//	}

//	@RequestMapping(value="/searchId", method= {RequestMethod.GET, RequestMethod.POST})
//	public String searchId() {
//		return "/searchId";
//	}
//
//	@RequestMapping(value="/searchPw", method= {RequestMethod.GET, RequestMethod.POST})
//	public String searchPw() {
//		return "/searchPw";
//	}
}
