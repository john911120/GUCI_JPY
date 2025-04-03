package com.guci.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrderVO {
	private Long orderId;
	private String userId, orderRec, orderAddr1, orderAddr2, orderAddr3, orderPhone;
	private Date orderDate;
	private String delivery, orderStock;
	private Long gdsNo, cartNo;
	private String gdsName;
}
