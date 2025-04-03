package com.guci.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsCriteria2 {

	private int pageNum;
	private int amount;

	private String keyword;

	public GoodsCriteria2() {
		this(1,9);
	}

	public GoodsCriteria2(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				//queryParam 메소드를 통해 필요한 파라미터를 손쉽게 추가
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("keyword", this.getKeyword());

		return builder.toUriString();
	}
}
