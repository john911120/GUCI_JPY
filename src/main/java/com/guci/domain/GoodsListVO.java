package com.guci.domain;

import java.util.Date;

import lombok.Data;

@Data
public class GoodsListVO {
	/*
	商品番号	 
	private Long gdsNo;
	
	商品名
	private String gdsName;
	
	商品価格	
	private Long gdsPrice;
	
	商品割引	
	private String gdsDes;
	
	商品のサイズ	
	private String gdsSize;
	
	商品登録日
	private Date gdsDate;
	
	商品プリビュー
	private Long gdsViews;
	
	商品カテゴリー
	private String cateCode;

	商品カテゴリー名
	private String cateName;

	商品上位カテゴリコード（参照元）
	private String cateCodeRef;
	
	商品カテゴリレベル
	private int level;
	 */
	
	private Long gdsNo;
	private String gdsName;
	private Long gdsPrice;
	private String gdsDes;
	private String gdsSize;
	private Date gdsDate;
	private Long gdsViews;
	private String cateCode;

	private String cateName;
	private String cateCodeRef;
	private int level;
}
