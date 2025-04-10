package com.guci.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//商品のレビューページにおけるページング処理を行うクラス
public class ReviewCriteria {

	// 現在のページ番号（デフォルトは1）
	private int pageNum;
	// 1ページあたりの表示件数（デフォルトは9）
	private int amount;
	
	// 商品番号
	private Long gdsNo;

	// 商品タイプ
	private String type;
	// 商品キーワード
	private String keyword;

	// デフォルトコンストラクタ（1ページ目、9件表示）
	public ReviewCriteria() {
		this(1,9);
	}
	
	// ページ番号と表示件数を指定して初期化
	public ReviewCriteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// typeが複数の場合に対応するため、1文字ずつの配列に分割
	public String[] getTypeArr(){
		return type == null ? new String[] {}: type.split("");
	}

	// 現在の検索・ページング情報を基にしたURI文字列を生成
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				//queryParam 메소드를 통해 필요한 파라미터를 손쉽게 추가
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());

		return builder.toUriString();
	}
}
