package com.guci.mapper;

import java.util.List;

import com.guci.domain.Criteria;
import com.guci.domain.ReviewCriteria;
import com.guci.domain.ReviewImgUserVO;

//import org.apache.ibatis.annotations.Select;

import com.guci.domain.ReviewVO;

public interface ReviewMapper {

	// 商品に対するすべてのレビューを取得する
	public List<ReviewVO> getList(Long gdsNo);

	// ページング付きで商品レビューを取得する
	public List<ReviewVO> getListWithPaging(Criteria cri);

	// レビューを登録する
	public void insert(ReviewVO review);

	// レビューを登録し、自動生成された主キーを取得する
	public void insertSelectKey(ReviewVO review);

	// レビュー情報をレビュー番号で取得する
	public ReviewVO read(Long revNo);

	// レビューを削除し、添付ファイルも同時に削除する
	public int delete(Long revNo);

	// レビューを更新する
	public int update(ReviewVO review);

	// 指定された条件でレビューの総件数を取得する
	public int getTotalCount(ReviewCriteria cri);

	// 商品詳細ページで表示するレビューおよび添付画像をページング付きで取得する
	public List<ReviewImgUserVO> getListImgWithPaging(ReviewCriteria cri);



}
