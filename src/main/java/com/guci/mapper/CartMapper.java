package com.guci.mapper;

import java.util.List;

import com.guci.domain.CartVO;
import com.guci.domain.TestVO;

public interface CartMapper {

	/*
	  カート関連のデータベース操作を行うマッパーインターフェースです。
	 
	  addCart：商品をカートに追加します（INSERT）
	            ※ 戻り値なし
	 
	  cartList：ユーザーごとにカート内の商品を取得します。
	            @return カートに入っている商品のリスト（List<CartVO>）
	 
	  deleteCart：ユーザーがカートに追加した商品を削除します（DELETE）
	               ※ 戻り値なし
	 
	  sumCart：カートに追加された商品の総金額を取得します（SELECT SUM）
	            @return 総金額（Long型）
	 */

	public void addCart(CartVO cart);

	public List<CartVO> cartList (String userId);

	public void deleteCart(TestVO vo);

	public Long sumCart(String userId);
}
