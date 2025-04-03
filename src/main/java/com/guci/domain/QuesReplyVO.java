package com.guci.domain;

import java.util.Date;

import lombok.Data;
//(388) VO 생성
@Data
public class QuesReplyVO {

	private Long rno;
	private Long quesNo;

	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;

}
