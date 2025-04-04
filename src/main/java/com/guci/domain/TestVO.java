package com.guci.domain;

import lombok.Data;

@Data
public class TestVO {
	
	/*
	 ユーザーID：注文を行ったユーザーの識別子  
	 カート番号：削除対象となるカート内商品の識別子
	*/
	private int cartNo;
	private String userId;
}
