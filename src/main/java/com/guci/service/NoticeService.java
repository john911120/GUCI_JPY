package com.guci.service;

import java.util.List;

import com.guci.domain.Criteria;
import com.guci.domain.NoticeVO;

public interface NoticeService {
	/*
	  お知らせの記事を管理するインターフェース
	  各メソッドはコントローラーから呼び出され、ビジネスロジックを担当する。
	 */
	
	//記事を登録（登録時に自動生成される主キーを含む）
	public void register(NoticeVO notice);

	//記事の詳細情報を取得
	public NoticeVO get(Long noticeNo);

	//記事を更新する
	public boolean modify(NoticeVO notice);
	
	//記事を削除する
	public boolean remove(Long noticeNo);

	//ページング付きでお知らせ記事を取得
	public List<NoticeVO> getList(Criteria cri);

	//総記事数を取得（ページングのため）
	public int getTotal(Criteria cri);
	
}

