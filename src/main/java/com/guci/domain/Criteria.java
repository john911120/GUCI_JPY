package com.guci.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/*
  ページネーションおよび検索条件を保持するクラス
 */
@Getter
@Setter
@ToString
public class Criteria {
	
	// 現在のページ番号
	private int pageNum;
	
	// 1ページあたりの表示件数
	private int amount;

	// 検索条件の種類（例：T=タイトル、C=内容など）
	private String type;
	
	// 検索キーワード
	private String keyword;

	// デフォルトコンストラクタ（1ページ目、9件表示）
	public Criteria() {
		this(1,9);
	}
	
	// ページ番号と表示件数を指定するコンストラクタ
	public Criteria(int pageNum, int amount) {
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
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());

		return builder.toUriString();
	}
}
