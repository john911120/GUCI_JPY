package com.guci.domain;

import lombok.Data;

@Data
public class ReviewAttachVO {
	private String uuid, uploadPath, fileName;
	private boolean fileType;
	private long revNo;
}
