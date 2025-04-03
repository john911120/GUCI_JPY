package com.guci.domain;


import java.util.Date;

import lombok.Data;

@Data
public class FaqVO {
	private Long faqNo;
	private String faqCate, faqTit, faqCon, faqWri;
	private Date faqDate;
}
