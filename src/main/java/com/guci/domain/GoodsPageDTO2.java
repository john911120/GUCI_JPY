package com.guci.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GoodsPageDTO2 {

	private int startPage;
	private int endPage;
	private boolean prev, next;

	private int total;
	private GoodsCriteria2 cri;


	public GoodsPageDTO2(GoodsCriteria2 cri, int total) {
		this.cri = cri;
		this.total = total;

		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;

		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}

}
