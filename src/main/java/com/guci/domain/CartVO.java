package com.guci.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CartVO {


	private int cartNo;
	private String userId;
	private int gdsNo;
	private String gdsName;
	private int cartStock;
	private Date cartDate;
	private String selSize;

	private String uuid,fileName,uploadPath;
	private Long gdsPrice;


}
