package com.guci.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuesCriteria {
	
	// 現在のページ番号
	private int pageNum; 
	
	// 1ページあたりの表示件数
	private int amount; 

	// 検索条件の種類（例：T=タイトル、C=内容など）
	private String type;
	// 検索キーワード
	private String keyword;

	// Q&Aコンストラクタ（1ページ目、9件表示）
	public QuesCriteria() {
		this(1, 10);
	}

	// Q&Aページ番号と表示件数を指定するコンストラクタ
	public QuesCriteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	// typeが複数の場合に対応するため、1文字ずつの配列に分割
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
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
