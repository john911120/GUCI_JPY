package com.guci.domain;

import lombok.Data;

@Data
public class CategoryVO {
	/*
	商品カテゴリー名
	private String cateName;
	
	商品カテゴリー
	private String cateCode;
	
	商品上位カテゴリコード（参照元）
	private String cateCodeRef;
	
	商品カテゴリーレベル
	private int level;
	 */
	private String cateName;
	private String cateCode;
	private String cateCodeRef;
	private int level;
}
