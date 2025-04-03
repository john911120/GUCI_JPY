package com.guci.domain;

import lombok.Data;

@Data
public class QuesAttachFileDTO {
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;
}
