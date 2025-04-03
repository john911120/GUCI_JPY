package com.guci.service;

import java.util.List;

import com.guci.domain.LatelyOrderVO;

/*
	管理者ダッシュボードに関するビジネスロジックのインターフェース
*/
public interface AdminService {
	/*
	questionNo : 未回答の質問件数を取得します。
		@return 質問件数（int型）
	
	totalIncome : 累積売上金額を取得します。
		 @return 売上金額（int型）
	
	todayIncome : 本日の売上金額を取得します。
		@return 売上金額（String型、nullの可能性あり）
		
	todaySalesQuantity : 本日の販売数量を取得します。
		@return 販売数量（String型、nullの可能性あり）
	
	latelyOrderList : 最近の注文リストを取得します。
		@return LatelyOrderVOのリスト
*/
	
	
	public int questionNo(); //未回答の質問件数取得
	public int totalIncome(); //累積売上金額を取得
	public String todayIncome(); //本日の売上金額を取得
	public String todaySalesQuantity(); // 本日の販売数量を取得

	// 最近注文リスト取得
	public List<LatelyOrderVO> latelyOrderList();
}
