package com.guci.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

/*
 「MainControllerは主にルーティング専用の構成で、ユーザーの画面遷移に対応するのみです。業務ロジックを含まず、機能単位で他のControllerと分離するために構築しました。」 
  ページの表示遷移を担当するコントローラーであり、ビジネスロジックは含まず、URLとビューのマッピングのみを行います。例として `/checkout` リクエストでは `checkout.jsp` に遷移します。
*/
	
	
	@RequestMapping(value="/checkout", method={RequestMethod.GET, RequestMethod.POST})
	public String checkout() {
		return "/checkout";
	}


	@RequestMapping(value="/orderlist", method={RequestMethod.GET, RequestMethod.POST})
	public String orderlist() {
		return "/orderlist";
	}

	@RequestMapping(value="/mypage_cart", method={RequestMethod.GET, RequestMethod.POST})
	public String mypage_cart() {
		return "/mypage_cart";
	}


	@RequestMapping(value="/exchange", method= {RequestMethod.GET, RequestMethod.POST})
	public String exchange() {
		return "/exchange";
	}

	@RequestMapping(value="/questionList", method= {RequestMethod.GET, RequestMethod.POST})
	public String questionList() {
		return "/questionList";
	}

}
