package com.guci.service;

import java.util.List;

import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesReplyPageDTO;
import com.guci.domain.QuesReplyVO;

public interface QuesReplyService {
	/*
	   Q&Aの記事コメントを管理するインターフェース
	  各メソッドはコントローラーから呼び出され、ビジネスロジックを担当する。
	 */
	
	// Q&Aの記事コメントを登録する
	public int register(QuesReplyVO vo);
	
	// Q&Aの記事コメント情報を取得する
	public QuesReplyVO get(Long rno);
	
	// Q&Aの記事コメントを修正する
	public int modify(QuesReplyVO vo);

	// Q&Aの記事コメントを削除する
	public int remove(Long rno);

	// Q&Aの記事コメントリスト情報を取得する
	public List<QuesReplyVO> getList(QuesCriteria cri, Long quesNo);

	// Q&Aの記事ページリストを取得（ページングのため）
	public QuesReplyPageDTO getListPage(QuesCriteria cri, Long quesNo);
}
