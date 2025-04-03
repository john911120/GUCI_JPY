package com.guci.mapper;

import java.util.List;

import com.guci.domain.LatelyOrderVO;
import com.guci.domain.OrderListVO;

/*
 * 管理者ダッシュボード関連のSQLマッパー
 */
public interface AdminMapper {
	/*
		totalIncome : 累積売上金額を取得します。
			@return 売上金額（int型、単位：ウォン）
		
		todayIncome : 当日の売上金額を取得します。
			@return 売上金額（String型、nullの可能性あり）
			
		todaySalesQuantity : 当日の販売数量を取得します。
			 @return 販売数量（String型、nullの可能性あり）
		
		questionNo : 未回答の質問件数を取得します
			 @return 質問件数（int型）
			 
		memberList : 会員別注文履歴リストを取得します。
			@return OrderListVOのリスト
		
		latelyOrderList : 最近の注文リストを取得します。
			@return LatelyOrderVOのリスト
	*/
	
	public int totalIncome();
	public String todayIncome();
	public String todaySalesQuantity();
	public int questionNo();

	public List<OrderListVO> memberList();

	public List<LatelyOrderVO> latelyOrderList();
}
