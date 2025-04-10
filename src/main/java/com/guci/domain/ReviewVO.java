package com.guci.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReviewVO {
	
	// レビュー記事番号
	private Long revNo;

	// 商品番号
	private Long gdsNo;

	// 商品名
	private String gdsName;

	// ユーザーID
	private String userId;

	// レビュー内容
	private String revCon;

	// 作成日
	private Date revDate;

	// レート
	private String score;

	// 添付ファイルリスト
	private List<ReviewAttachVO> attachList;

}
