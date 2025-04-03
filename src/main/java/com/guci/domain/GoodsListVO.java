package com.guci.domain;

import java.util.Date;

import lombok.Data;

@Data
public class GoodsListVO {
	private Long gdsNo;
	private String gdsName;
	private Long gdsPrice;
	private String gdsDes;
	private String gdsSize;
	private Date gdsDate;
	private Long gdsViews;
	private String cateCode;

	private String cateName, cateCodeRef;
	private int level;
}
