package com.guci.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
   Q&A（質問掲示板）に関するVOクラス
  - 質問の基本情報（タイトル、内容、作成者、作成日など）
  - コメント（返信）の管理
  - 添付ファイルリストの保持
 */
@Data
public class QuesVO {
	
		// コメント（返信）番号
		private Long replyNo;

		// 質問記事番号（主キー）
		private Long quesNo;

		// 質問タイトル
		private String quesTit;

		// 質問内容（本文）
		private String quesCon;

		// 作成者（ユーザーIDまたはニックネーム）
		private String quesWri;

		// 作成日（登録日時）
		private Date quesDate;

		// 質問カテゴリーコード（例：配送、返品、決済など）
		private String quesCateCode;

		// コメント数（質問に対する返信の件数）
		private int replyCnt;

		// 添付ファイルリスト（複数ファイル対応）
		private List<QuesAttachVO> attachList;
}
