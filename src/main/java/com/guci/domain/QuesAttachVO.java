package com.guci.domain;

import lombok.Data;

/*
  質問記事に添付されたファイルの情報を保持するVO
   - ファイルの保存経路、識別子、ファイル種別などを管理
 */
@Data
public class QuesAttachVO {
		// ファイル識別用のUUID
		private String uuid;

		// アップロードされたファイルの保存パス
		private String uploadPath;

		// ファイル名（オリジナル名）
		private String fileName;

		// ファイルタイプ（true：画像, false：その他）
		private boolean fileType;

		// 対象となる質問記事の番号
		private Long quesNo;
}
