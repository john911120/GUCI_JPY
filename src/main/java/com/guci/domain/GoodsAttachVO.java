package com.guci.domain;

import lombok.Data;

/*
	商品の添付ファイルに関するデータモデル
	各項目は商品の添付ファイル管理・表示に使用されます。
*/
@Data
public class GoodsAttachVO {
/*
 	添付ファイルを識別するコード
	private String uuid;
	
	添付ファイルアップロードパス
	private String uploadPath;
	
	添付ファイル名
	private String fileName;
	
	添付ファイルタイプ
	private boolean fileType;
	
	商品番号
	private Long gdsNo;
	
	商品名	
	private String gdsName;
*/
	private String uuid;
	private String uploadPath;
	private String fileName;
	
	private boolean fileType;
	private Long gdsNo;
	private String gdsName;
}
