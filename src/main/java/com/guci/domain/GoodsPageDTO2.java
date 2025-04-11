package com.guci.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GoodsPageDTO2 {

	// 表示される最初のページ番号
	private int startPage;

	// 表示される最後のページ番号
	private int endPage;

	// 「前へ」ボタンの有無
	private boolean prev;

	// 「次へ」ボタンの有無
	private boolean next;

	// 検索結果の総件数
	private int total;

	// 検索条件を含むページ情報
	private GoodsCriteria2 cri;

	// コンストラクタ：ページネーションの各値を初期化
	public GoodsPageDTO2(GoodsCriteria2 cri, int total) {
		this.cri = cri;
		this.total = total;

		/* 
		 表示される最後のページ番号（10ページ単位）
		 表示される最初のページ番号
		 */
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;

		// 実際の最終ページ番号を計算
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		// 表示上の最終ページ番号を調整
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}

		/*
		「前へ」リンクを表示するか
		「次へ」リンクを表示するか
		*/
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}