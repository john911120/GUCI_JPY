package com.guci.service;

import java.util.List;

import com.guci.domain.QuesAttachVO;
import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesVO;

public interface QuesService {
	/*
	   Q&Aの記事を管理するインターフェース
	  各メソッドはコントローラーから呼び出され、ビジネスロジックを担当する。
	 */
	
	// Q&A記事を登録し、登録された主キー（quesNo）を取得してリダイレクトします
	public void register(QuesVO board);

	// Q&A記事の詳細を取得します
	public QuesVO get(Long quesNo); 

	// Q&A記事を更新します
	public boolean modify(QuesVO board); 

	// Q&A記事を削除します
	public boolean remove(Long quesNo); 

	// 特定のQ&A記事に対するコメント一覧
	public List<QuesVO> getList(QuesCriteria cri); 

	// Q&Aの総記事数を取得（ページングのため）
	public int getTotal(QuesCriteria cri); 

	// Q&A記事と添付ファイルを取得します
	public List<QuesAttachVO> getAttachList(Long quesNo);




    }
