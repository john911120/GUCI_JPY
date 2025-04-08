package com.guci.mapper;

import com.guci.domain.OrderInfoVO;
import com.guci.domain.OrderVO;

public interface OrderMapper {
	/*
	  商品注文に関するデータベース操作を担当するMapperインターフェース
	  MyBatisを通じてSQLと連携する
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
