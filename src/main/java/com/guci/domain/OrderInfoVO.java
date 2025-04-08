package com.guci.domain;

import lombok.Data;

/*
  注文情報に関するデータモデル
  各項目は商品の注文情報管理・表示に使用されます。
 */
@Data
public class OrderInfoVO {
	/*
	ユーザーID : 注文が行ったユーザの識別子
	private String userId;	

	商品注文の履歴
	private String orderRec;
	
	商品送るための住所1(日本の都道府県と同じ)
	private String orderAddr1;
	
	商品送るための住所2(日本の市区町村と同じ)
	private String orderAddr2;
	
	商品送るための住所3(日本の番地、号、建物名と同じ)
	private String orderAddr3;
	
	商品を受け取るユーザー連絡先
	private String orderPhone;
	
	*/
	
	private String userId;
	
	private String orderRec;
	
	private String orderAddr1;
	
	private String orderAddr2;
	
	private String orderAddr3;
	
	private String orderPhone;
}
