package com.guci.domain;

import lombok.Data;

@Data
public class GoodsAttachVO {
	private String uuid, uploadPath, fileName;
	private boolean fileType;
	private Long gdsNo;
	private String gdsName;
}
