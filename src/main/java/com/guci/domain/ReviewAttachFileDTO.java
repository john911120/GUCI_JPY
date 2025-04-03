package com.guci.domain;

import lombok.Data;

@Data
public class ReviewAttachFileDTO {
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;
}
