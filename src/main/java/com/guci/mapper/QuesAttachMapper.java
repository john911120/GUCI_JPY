package com.guci.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guci.domain.QuesAttachVO;

public interface QuesAttachMapper {

	// Q&A記事の添付ファイルを登録
	public void insert(QuesAttachVO vo);

	// 添付ファイルをUUIDで削除
	public void delete(String uuid);

	// Q&A記事番号に基づいて添付ファイルリストを取得
	public List<QuesAttachVO> findByquesNo(Long quesNo);

	// Q&A記事削除時に添付ファイルを一括削除
	public void deleteAll(@Param("quesNo") Long quesNo);

	// 一定期間経過した古い添付ファイルを取得（クリーンアップ目的）
	public List<QuesAttachVO> getOldFiles();
}
