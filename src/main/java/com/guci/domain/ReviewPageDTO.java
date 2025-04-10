package com.guci.domain;

import lombok.Getter;
import lombok.ToString;

/*
	レビューページネーションおよび検索条件を保持するクラス
*/
@Getter
@ToString
public class ReviewPageDTO {

	// 表示される最初のページ番号
	private int startPage;

	// 表示される最後のページ番号
	private int endPage;

	// 「前へ」ボタンの表示可否
	private boolean prev;

	// 「次へ」ボタンの表示可否
	private boolean next;

	// 総レビュー件数
	private int total;

	// ページングに必要な条件（現在のページ番号、1ページ当たりの表示数など）
	private ReviewCriteria cri;

	// ページング情報を初期化（レビュー件数と現在のページに基づく）
	public ReviewPageDTO(ReviewCriteria cri, int total) {
		this.cri = cri;
		this.total = total;

		// 例：現在ページが15の場合 → endPage = 20
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;

		// endPageに基づいてstartPage決定（10ページ単位）
		this.startPage = this.endPage - 9;

		// 実際の最終ページ番号を計算
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		// 実際の最終ページが表示されるendPageより小さい場合、endPageを修正
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}

		// 前へボタンの表示可否（startPageが1より大きいならtrue）
		this.prev = this.startPage > 1;

		// 次へボタンの表示可否（endPageが実際の最終ページより小さいならtrue）
		this.next = this.endPage < realEnd;
	}
}
