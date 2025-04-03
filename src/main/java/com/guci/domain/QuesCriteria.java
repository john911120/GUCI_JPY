package com.guci.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuesCriteria {
// 293 페이징 처리(Processing DTO)
	private int pageNum; // 페이지 번호
	private int amount; // 페이지 당 게시글 갯수

	// 검색 조건 처리를 위한 수정 및 변수 설정
	private String type;
	private String keyword;

	// 1페이지 당 10개의 게시글을 보여주겠다.
	public QuesCriteria() {
		this(1, 10);
	}

	public QuesCriteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	// p 334
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	// uricomponentsbuilder 이용 링크 생성
	// p.349
	// (580) 게시물 삭제 후 페이지 번호 검색 조건을 유지하기 위한 코드를 체크한다.
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
			.queryParam("pageNum", this.pageNum)
			.queryParam("amount", this.getAmount())
			.queryParam("type", this.getType())
			.queryParam("keyword", this.getKeyword());

		return builder.toUriString();
	}

}
