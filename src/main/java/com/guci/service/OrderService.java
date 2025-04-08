package com.guci.service;

import com.guci.domain.OrderInfoVO;
import com.guci.domain.OrderVO;

public interface OrderService {
	/*
  	   商品注文を管理するインターフェース
		各メソッドはコントローラーから呼び出され、ビジネスロジックを担当する。
	 */ 

	// 注文情報を追加します。
	public void insertInfo(OrderInfoVO ivo);

	// 注文商品を追加します。
	public void orderInsert(OrderVO vo);

	// 注文情報を削除します。
	public int deleteInfo(String userId);

	// カートに入った商品を全部削除します。
	public int cartAllDelete(String userId);

	//ユーザーデーブルの総金額を更新します。
	public int updateUser(String userId);
}
