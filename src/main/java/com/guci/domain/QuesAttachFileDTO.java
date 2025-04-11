package com.guci.domain;

import lombok.Data;

@Data
public class QuesAttachFileDTO {
	/*
	ファイル名（オリジナル名）
	private String fileName;
	
	アップロードされたファイルの保存パス
	private String uploadPath;
	
	ファイル識別用のUUID
	private String uuid;
	
	ファイルのイメージ
	private boolean image;
	 */
	
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;
}
