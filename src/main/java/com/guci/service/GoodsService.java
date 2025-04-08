package com.guci.service;

import java.util.List;

import com.guci.domain.CategoryVO;
import com.guci.domain.GoodsAttachVO;
import com.guci.domain.GoodsCriteria1;
import com.guci.domain.GoodsCriteria2;
import com.guci.domain.GoodsListAttachVO;
import com.guci.domain.GoodsListVO;
import com.guci.domain.GoodsVO;

//商品関連サービスのインターフェース
public interface GoodsService {
	
	// 商品カテゴリ一覧を取得
	public List<CategoryVO> category_goods();

	// 商品情報を登録
	public void register_goods(GoodsVO goods);
	
	// 商品情報を取得（JOINを含む場合あり）
	public GoodsListVO get_goods(Long gdsNo);
	public GoodsVO get_goods_no_join(Long gdsNo);
	
	// 商品情報を更新
	public boolean modify_goods(GoodsVO goods);
	
	// 商品情報を削除
	public boolean remove_goods(Long gdsNo);
	
	// 商品一覧を取得
	public List<GoodsListVO> getList_goods();

	// 商品詳細ページ表示用のデータを取得
	public GoodsListAttachVO get_goods_detail(Long gdsNo) throws Exception;

	// 指定商品の添付ファイル情報を取得
	public List<GoodsAttachVO> getAttachList(Long gdsNo);


	// 各タブ（ベスト、新着、男性、女性、ユニセックス）に表示する商品一覧を取得
	public List<GoodsListAttachVO> getList_best(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getList_new(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getList_man(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getList_woman(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getList_unisex(GoodsCriteria1 cri);

	// 各カテゴリの総商品数を取得（ページネーション用）
	public int getTotal_best(GoodsCriteria1 cri);
	public int getTotal_new(GoodsCriteria1 cri);
	public int getTotal_man(GoodsCriteria1 cri);
	public int getTotal_woman(GoodsCriteria1 cri);
	public int getTotal_unisex(GoodsCriteria1 cri);

	// 商品検索結果を取得
	public List<GoodsListAttachVO> getList_search(GoodsCriteria2 cri);
	
	// 商品検索結果の総件数を取得
	public int getTotal_search(GoodsCriteria2 cri);
}
