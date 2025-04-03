package com.guci.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class GoodsVO {
	private Long gdsNo;
	private String gdsName;
	private Long gdsPrice;
	private String gdsDes;
	private String gdsSize;
	private Date gdsDate;
	private Long gdsViews;
	private String cateCode;

	private List<GoodsAttachVO> attachList;
}
