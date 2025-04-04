package com.guci.domain;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO {
	
	/*
	 	お知らせ番号 : お知らせ記事を一意に識別するための番号
		private Long noticeNo;
		
		お知らせタイトル : 記事のタイトルを表す
		private String noticeTit;
		
		お知らせ内容 : 記事の本文（詳細な内容）
		private String noticeCon;
		
		作成者 : 記事を作成したユーザーの名前
		private String noticeWri;
		
		作成日 : 記事が登録された日付
		private Date noticeDate;

	*/
	private Long noticeNo;
	private String noticeTit;
	private String noticeCon;
	private String noticeWri;
	private Date noticeDate;
}
