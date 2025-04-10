package com.guci.mapper;

import java.util.List;

import com.guci.domain.ReviewAttachVO;

public interface ReviewAttachMapper {
	
	// レビュー添付ファイルを登録する
	public void insert(ReviewAttachVO vo);
	
	// レビュー添付ファイルを削除する
	public void delete(String uuid);

	// レビュー記事番号で記事を検索する
	public List<ReviewAttachVO> findByRevNo(Long revNo);
	
	// レビュー添付ファイルを全部削除する
	public void deleteAll(Long revNo);

	// 古い商品のレビュー添付ファイル情報を取得する。
	public List<ReviewAttachVO> getOldFiles();
	
}
