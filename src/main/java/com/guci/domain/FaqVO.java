package com.guci.domain;


import java.util.Date;

import lombok.Data;

/*
 * FAQ（よくある質問）に関するデータモデル
 * 各項目は記事の管理・表示に使用されます。
 */

@Data
public class FaqVO {
	/*
	質問記事を一意に識別するための番号
	private Long faqNo;
	
	質問のカテゴリ（例：注文、配送、返品など）
	private String faqCate;
	
	記事タイトル
	private String faqTit;
	
	記事の内容（詳細）
	private String faqCon;
	
	作成者のユーザー名
	private String faqWri;
	
	記事の作成日（登録日）
	private Date faqDate;
	
	*/
	private Long faqNo;
	
	private String faqCate;
	private String faqTit;
	private String faqCon;
	private String faqWri;
	
	private Date faqDate;
}
