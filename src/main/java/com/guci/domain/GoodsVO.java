package com.guci.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

/*
	商品に関するデータモデル
	各項目は商品管理・表示に使用されます。
*/

@Data
public class GoodsVO {
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

	商品添付ファイルリスト
	private List<GoodsAttachVO> attachList;
	
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
}
