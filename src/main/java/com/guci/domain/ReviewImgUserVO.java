package com.guci.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReviewImgUserVO {

	// レビュー番号（主キー）
	private Long revNo;

	// 商品番号
	private Long gdsNo;

	// 商品名
	private String gdsName;

	// レビュー内容（本文）
	private String revCon;

	// レビュー作成日
	private Date revDate;

	// レビューの評価（星の数）
	private String score;

	// 添付ファイルリスト（画像など）
	private List<ReviewAttachVO> attachList;

	// 添付ファイルのUUID
	private String uuid;

	// 添付ファイルの保存パス
	private String uploadPath;

	// 添付ファイル名
	private String fileName;

	// ファイルの種類（画像かどうか）
	private boolean fileType;

	// ユーザーID（レビュー作成者）
	private String userId;
}
