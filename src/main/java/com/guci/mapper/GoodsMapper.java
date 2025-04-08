package com.guci.mapper;

import java.util.List;

import com.guci.domain.CategoryVO;
import com.guci.domain.GoodsCriteria1;
import com.guci.domain.GoodsCriteria2;
import com.guci.domain.GoodsListAttachVO;
import com.guci.domain.GoodsListVO;
import com.guci.domain.GoodsVO;

public interface GoodsMapper {
	// 商品カテゴリ一覧を取得
	public List<CategoryVO> category_goods();

	// 商品一覧を取得（管理用）
	public List<GoodsListVO> getList_goods();

	// 商品情報を登録（通常登録）
	public void insert_goods(GoodsVO goods);
	
	// 商品情報を登録し、自動生成されたキーを取得
	public void insertSelectKey_goods(GoodsVO goods);
	
	// 商品情報を取得（JOINあり）
	public GoodsListVO read_goods(Long gdsNo);
	
	// 商品情報を取得（JOINなし）
	public GoodsVO read_goods_no_join(Long gdsNo);

	// 商品詳細ページ用の情報を取得
	public GoodsListAttachVO read_goods_detail(Long gdsNo);

	// 商品を削除
	public int delete_goods(Long gdsNo);
	
	// 商品情報を更新
	public int update_goods(GoodsVO goods);


	// 各種商品タブの一覧を取得（ページングなし）
	public List<GoodsListAttachVO> getList_best();
	public List<GoodsListAttachVO> getList_new();
	public List<GoodsListAttachVO> getList_man();
	public List<GoodsListAttachVO> getList_woman();
	public List<GoodsListAttachVO> getList_unisex();


	// 各種商品タブの一覧を取得（ページングあり）
	public List<GoodsListAttachVO> getListWithPaging_best(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getListWithPaging_new(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getListWithPaging_man(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getListWithPaging_woman(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getListWithPaging_unisex(GoodsCriteria1 cri);

	// 各タブの総件数を取得（ページネーション用）
	public int getTotalCount_best(GoodsCriteria1 cri);
	public int getTotalCount_new(GoodsCriteria1 cri);
	public int getTotalCount_man(GoodsCriteria1 cri);
	public int getTotalCount_woman(GoodsCriteria1 cri);
	public int getTotalCount_unisex(GoodsCriteria1 cri);



	// 商品検索結果の取得（ページング付き）
	public List<GoodsListAttachVO> getListWithPaging_search(GoodsCriteria2 cri);
	
	// 商品検索結果の総件数を取得
	public int getTotalCount_search(GoodsCriteria2 cri);

	// 商品の閲覧数をカウントアップ
	public void gdsViews(Long gdsNo) throws Exception;
}
