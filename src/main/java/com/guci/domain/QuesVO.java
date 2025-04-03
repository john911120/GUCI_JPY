package com.guci.domain;
// 183
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class QuesVO {
	private Long replyNo;
	private Long quesNo;

	private String quesTit;
	private String quesCon;
	private String quesWri;
	private Date quesDate;
	private String quesCateCode;


	//(481) 댓글과 댓글 수 처리
	private int replyCnt;

	// (552) BoardAttachVO를 처리할 수 있도록 코드 추가
	private List<QuesAttachVO> attachList;
}
