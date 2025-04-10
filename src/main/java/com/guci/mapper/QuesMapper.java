package com.guci.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesVO;

public interface QuesMapper {

	// Q&A記事一覧を取得（コメント付き）
	public List<QuesVO> getlist();
	
	// ページング付きでQ&A記事一覧を取得
	public List<QuesVO> getListWithPaging(QuesCriteria cri);

	// Q&A記事を新規登録
	public void insert(QuesVO board);

	// Q&A記事を新規登録し、自動生成された主キーを取得
	public Integer insertSelectKey(QuesVO board);

	// Q&A記事を詳細表示（ID指定）
	public QuesVO read(Long quesNo);

	// Q&A記事を削除
	public int delete(Long quesNo);

	// Q&A記事を修正
	public int update(QuesVO board);

	// 全体Q&A記事数を取得（ページングのため）
	public int getTotalCount(QuesCriteria cri);

	// 該当Q&A記事のコメント数を増減
	public void updateReplyCnt(@Param("quesNo") Long quesNo, @Param("amount") int amount);
}
