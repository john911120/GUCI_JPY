package com.guci.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class GoodsAndImgCateVO {
	/*
	商品番号
	private Long gdsNo;
	
	商品名
	private String gdsName;
	
	商品価格
	private Long gdsPrice;
	
	商品割引	
	private String gdsDes;
	
	商品サイズ
	private String gdsSize;
	
	商品登録日
	private Date gdsDate;
	
	商品プリビュー
	private Long gdsViews;
	
	商品カテゴリー
	private String cateCode;

	添付ファイルリスト（画像など）
	private List<GoodsAttachVO> attachList;

	添付ファイルを識別するコード
	private String uuid;
	
	添付ファイルをアップできる経路
	private String uploadPath;
	
	添付ファイルの名称	
	private String fileName;
	
	添付ファイルタイプの名称
	private boolean fileType;

	商品上位カテゴリコード（参照元）
	private String cateCodeRef;
	
	商品カテゴリー名	
	private String cateName;
	*/
	private Long gdsNo;
	private String gdsName;
	private Long gdsPrice;
	private String gdsDes;
	private String gdsSize;
	private Date gdsDate;
	private Long gdsViews;
	private String cateCode;
	
	private List<GoodsAttachVO> attachList;

	private String uuid;
	private String uploadPath;
	private String fileName;
	
	private boolean fileType;

	private String cateCodeRef;
	private String cateName;
}
