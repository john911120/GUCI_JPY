package com.guci.domain;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO {

	private Long noticeNo;
	private String noticeTit;
	private String noticeCon;
	private String noticeWri;
	private Date noticeDate;
}
