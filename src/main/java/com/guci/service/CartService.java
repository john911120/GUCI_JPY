package com.guci.service;


import java.util.List;

import com.guci.domain.CartVO;
import com.guci.domain.TestVO;
/*
	注文に使うカートを管理するためのインターフェース
*/
public interface CartService {

	/*
	 addCart : ユーザー情報を確認して、商品をカートに追加します。
	 ※ 戻り値なし
 	 cartList : ユーザーごとにカートに追加した商品を取得します。
 	 @return カートに入っている商品のリスト（List<CartVO>）
	 deleteCart : ユーザーがカートに追加した商品を削除します。
 	 ※ 戻り値なし
	 sumCart : カートに追加された商品の総金額を取得します。
 	 @return 総金額（Long型） 
	*/
	
	public void addCart(CartVO cart);

	public List<CartVO> cartList(String userId);

	public void deleteCart(TestVO vo);

	public Long sumCart(String userId);
}
