package com.guci.domain;

import java.util.Date;

import lombok.Data;

/*
	商品名 : 注文された商品の名称
	private String gdsName;
	
	ユーザーID : 注文が行ったユーザの識別子
	private String userId;
	
	在庫数 : 該当商品の現在の在庫数量
	private Long stock;
	
	価格 : 1個あたりの販売価格、単位：ウォン
	private Long price;
	
	注文日 : 注文が行われた日付、形式：YYYY-MM-DD
	private Date orderDate;
*/
@Data
public class LatelyOrderVO {
	private String gdsName;
	
	private String userId;
	
	private Long stock;
	
	private Long price;
	
	private Date orderDate;
}
