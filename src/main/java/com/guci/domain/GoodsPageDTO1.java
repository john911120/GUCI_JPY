package com.guci.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GoodsPageDTO1 {

	// 表示される最初のページ番号
	private int startPage;

	// 表示される最後のページ番号
	private int endPage;

	// 「前へ」ボタンの有無
	private boolean prev;

	// 「次へ」ボタンの有無
	private boolean next;

	// 総データ件数
	private int total;

	// 現在のページ情報と表示件数
	private GoodsCriteria1 cri;

	// コンストラクタ：ページネーションの計算を実行
	public GoodsPageDTO1(GoodsCriteria1 cri, int total) {
		this.cri = cri;
		this.total = total;

		/*
		現在のページを基に、表示するページの終了番号を計算（10ページ単位）
		ページの開始番号を計算（10ページ単位）
		*/ 
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;

		// 実際の最終ページ番号を計算
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		// 表示すべき最終ページ番号を調整
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}

		/*
		「前へ」リンクの有無
		「次へ」リンクの有無
		*/ 
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}

}
