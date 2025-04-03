package com.guci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guci.domain.LatelyOrderVO;
import com.guci.domain.MemberListVO;
import com.guci.service.AdminService;
import com.guci.service.MemberListService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/*
   @RequestMapping：このURLでアクセスされた時に実行される処理

	会員一覧サービス
	@Autowired
	private MemberListService mService;
	
	管理者向けダッシュボードサービス
	@Autowired
	private AdminService service;
		
 */
@Controller
@Log4j
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

	@Autowired
	private MemberListService mService;

	@Autowired
	private AdminService service;
	
	/*
	   admin_index :
	   未回答の質問件数、最近の注文リスト、累積売り上げ金額、当日売り上げ金額、
	   当日販売数量を取得し、画面に渡す処理です。
	*/
	@RequestMapping(value="/index", method= {RequestMethod.GET,RequestMethod.POST})
	public void admin_index(Model model) {
		int questionNo = service.questionNo(); //質問件数
		List<LatelyOrderVO> latelyOrder = service.latelyOrderList(); // 最近の注文
		
		int totalIncome = service.totalIncome(); // 累積売上
		int todayIncome = 0;
		int todaySalesQuantity = 0;
		if(service.todayIncome() != null) {
			todayIncome = Integer.parseInt(service.todayIncome());
		}
		if(service.todaySalesQuantity() != null) {
			todaySalesQuantity = Integer.parseInt(service.todaySalesQuantity());
		}
		
		// モデルに値をセット
		model.addAttribute("questionNo", questionNo);
		model.addAttribute("latelyOrder", latelyOrder);
		model.addAttribute("totalIncome", totalIncome);
		model.addAttribute("todayIncome", todayIncome);
		model.addAttribute("todaySalesQuantity", todaySalesQuantity);

		log.info(latelyOrder);
	}


	// ４年前テストの目的に使われたコードです。現在は使われておりません。
	// 将来的な機能拡張または管理画面再構成の際に再利用される可能性があります。
/*	
    @RequestMapping(value="/admin/goods/list", method= {RequestMethod.GET,RequestMethod.POST})
	public String list() {
		return "/admin/goods/list";
	}

	@RequestMapping(value="/admin/goods/register", method= {RequestMethod.GET,RequestMethod.POST})
	public String register() {
		return "/admin/goods/register";
	}

	@RequestMapping(value="/deliveryInfo", method= {RequestMethod.GET,RequestMethod.POST})
	public String deliveryInfo() {
		return "/admin/deliveryInfo";
	}

	@RequestMapping(value="/exchange_refund", method= {RequestMethod.GET,RequestMethod.POST})
	public String exchange_refund() {
		return "/admin/exchange_refund";
	}
*/
	
	/*
	   memberList :
	   顧客情報（会員リスト）を取得し、画面に渡すための処理です。
	*/
	@RequestMapping(value="/memberList", method= RequestMethod.GET)
	public void memberList(Model model) {
		List<MemberListVO> list = mService.getList();  // 顧客リストの取得

		model.addAttribute("list", list); // 画面に渡す
	}
}
