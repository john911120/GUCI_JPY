package com.guci.domain;

import java.util.Date;

import lombok.Data;

/*
  商品注文に関するデータモデル
  各項目は商品の管理・表示に使用されます。
 */
@Data
public class OrderVO {
	/*
	商品の注文を識別するための番号
	private Long orderId;
	
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
	
	注文日
	private Date orderDate;
	
	商品配送のデータ
	private String delivery;
	
	注文に送る商品の在庫
	private String orderStock;
	
	商品番号
	private Long gdsNo;
	
	カート番号
	private Long cartNo;
	
	商品名
	private String gdsName;
	*/
	
	private Long orderId;
	
	private String userId;
	
	private String orderRec;
	
	private String orderAddr1;
	
	private String orderAddr2;
	
	private String orderAddr3;
	
	private String orderPhone;
	
	private Date orderDate;
	
	private String delivery;
	
	private String orderStock;
	
	private Long gdsNo;
	
	private Long cartNo;
	
	private String gdsName;
}
