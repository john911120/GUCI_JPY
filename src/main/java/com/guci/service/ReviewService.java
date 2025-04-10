  package com.guci.service;

import java.util.List;

import com.guci.domain.ReviewAttachVO;
import com.guci.domain.ReviewCriteria;
import com.guci.domain.ReviewImgUserVO;
import com.guci.domain.ReviewVO;

public interface ReviewService {
	/*
	   商品のレビューを管理するインターフェース
		各メソッドはコントローラーから呼び出され、ビジネスロジックを担当する。
	 */ 
	
	// レビュー登録処理（添付ファイル情報も含む）
	public void register(ReviewVO review);
	
	// レビュー記事をレビュー番号で取得する
	public ReviewVO get(Long revNo);

	// レビューを修正する
	public boolean modify(ReviewVO review);

	// レビュー削除時、添付ファイルも同時に削除する
	public boolean remove(Long revNo);

	// 商品に対するレビュー一覧を取得する
	public List<ReviewVO> getList(Long gdsNo);

	// 商品ごとのレビュー総件数を取得する（ページング対応）
	public int getTotal(ReviewCriteria cri);

	// 指定したレビューの添付ファイル一覧をJSON形式で取得する
	public List<ReviewAttachVO> getAttachList(Long revNo);

	// 商品詳細ページで表示するレビューと添付画像をページング付きで取得する
	public List<ReviewImgUserVO> getListImgWithPaging(ReviewCriteria cri);


}
