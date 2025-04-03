package com.guci.domain;

import java.util.Date;

import lombok.Data;

/*
	ユーザーID : 注文が行ったユーザの識別子
	private String userId;
	
	ユーザー名 : 注文が行ったユーザの名前
	private String userName;
	
	注文番号 :　注文の識別番号
	private int orderNum;
	
	注文価格 : 当該注文の合計金額
	private int orderPrice;
	
	登録日 : 注文が登録された日付（会員登録日などの可能性あり）
	private Date regDate;
	
	在庫数：商品を供給できる現在の数量
	private Long stock;
	
	合計数量：注文時に指定された商品の合計数量
	private Long sum;
*/
@Data
public class MemberListVO {
	private String userId;
	
	private String userName;
	
	private int orderNum;
	
	private int orderPrice;
	
	private Date regDate;
	
	private Long stock;
	
	private Long sum;
}
