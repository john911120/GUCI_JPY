package com.guci.domain;

import lombok.Data;

@Data
public class ReviewAttachVO {
	/*
	添付ファイルを識別するコード	
	private String uuid;

	添付ファイルアップロードパス
	private String uploadPath;

	添付ファイル名
	private String fileName;

	添付ファイルタイプ
	private boolean fileType;

	レビュー記事を識別するコード
	private long revNo;
	 */
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	private long revNo;
}
