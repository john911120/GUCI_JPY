package com.guci.domain;

import java.util.Date;

import lombok.Data;

/*
  	Q&Aの質問に対するコメント（返信）情報を保持するVO
  - 1つの質問に対して複数の返信が可能
 */
@Data
public class QuesReplyVO {

		// コメント番号（主キー）
		private Long rno;

		// 対象となる質問記事の番号
		private Long quesNo;

		// コメント内容
		private String reply;

		// コメント作成者（ユーザーIDまたは名前）
		private String replyer;

		// コメント作成日時
		private Date replyDate;

		// コメント更新日時
		private Date updateDate;
}
