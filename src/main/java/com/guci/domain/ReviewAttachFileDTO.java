package com.guci.domain;

import lombok.Data;

@Data
public class ReviewAttachFileDTO {
	
	/*
	添付ファイル名
 	private String fileName;
 	
 	添付ファイルアップロードパス
	private String uploadPath;
	
	添付ファイルを識別するコード		
	private String uuid;
	
	添付ファイルイメージ
	private boolean image;
	 */
	
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;
}
