package com.guci.domain;

import lombok.Data;

@Data
public class AttachFileDTO {
	/*
	UUID : 添付ファイルを識別するコード
	private String uuid;
	
	ファイルの名前 : 添付ファイルの名称
	private String fileName;
	
	ファイルアップロード経路 : 添付ファイルをアップできる経路
	private String uploadPath;
	
	イメージ : 添付ファイルのイメージ
	private boolean image;
	 */
	private String fileName;
	private String uploadPath;
	private String uuid;
	
	private boolean image;
}
