package com.guci.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReviewImgUserVO {
	private Long revNo;//글번호
	private Long gdsNo;//상품번호
	private String gdsName;//상품명

	private String revCon;//리뷰내용
	private Date revDate;//작성일자
	private String score;//별점
	private List<ReviewAttachVO> attachList;

	private String uuid, uploadPath, fileName;
	private boolean fileType;

	private String userId;
}
