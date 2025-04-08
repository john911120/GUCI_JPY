package com.guci.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//商品のキーワード別一覧ページにおけるページングおよびフィルター処理を行うクラス
public class GoodsCriteria2 {

	// 現在のページ番号（デフォルトは1）
	private int pageNum;

	// 1ページあたりの表示件数（デフォルトは9）
	private int amount;
	
	// 商品キーワードのフィルター条件
	private String keyword;

	// デフォルトコンストラクタ（1ページ目、9件表示）
	public GoodsCriteria2() {
		this(1,9);
	}
	
	// ページ番号と表示件数を指定して初期化
	public GoodsCriteria2(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	// 現在のページ情報とフィルターを含んだURLクエリ文字列を生成
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("keyword", this.getKeyword());

		return builder.toUriString();
	}
}
