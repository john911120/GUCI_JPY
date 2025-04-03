package com.guci.domain;

import java.util.Date;

import lombok.Data;

/*
	カート番号 : 注文するための商品の入れる
	private int cartNo;
	
	ユーザーID : 注文が行ったユーザの識別子
	private String userId;
	
	商品番号 : 注文するための商品の名の識別子
	private int gdsNo;
	
	商品名 : 注文するための商品の名称
	private String gdsName;
	
	カートに入る範囲 : 1カートに入られる範囲
	private int cartStock;
	
	カートに商品を追加した日 : 注文のためにカートに商品を入れた日付、形式：YYYY-MM-DD
	private Date cartDate;
	
	商品価格 : 注文するための商品の価格
	private Long gdsPrice;
	
	商品のサイズ : 商品の大きさの識別コード
	private String selSize;
	
	UUID : 添付ファイルを識別するコード
	private String uuid;
	
	ファイルの名前 : 添付ファイルの名称
	private String fileName;
	
	ファイルアップロード経路 : 添付ファイルをアップできる経路
	private String uploadPath;
	
*/

@Data
public class CartVO {

	private int cartNo;
	private String userId;
	private int gdsNo;
	private String gdsName;
	private int cartStock;
	private Date cartDate;
	private String selSize;
	private Long gdsPrice;

	private String uuid;
	private String fileName;
	private String uploadPath;


}
