package com.guci.mapper;

import java.util.List;

import com.guci.domain.CategoryVO;
import com.guci.domain.GoodsCriteria1;
import com.guci.domain.GoodsCriteria2;
import com.guci.domain.GoodsListAttachVO;
import com.guci.domain.GoodsListVO;
import com.guci.domain.GoodsVO;

public interface GoodsMapper {
	public List<CategoryVO> category_goods();

	public List<GoodsListVO> getList_goods();

	public void insert_goods(GoodsVO goods);
	public void insertSelectKey_goods(GoodsVO goods);

	public GoodsListVO read_goods(Long gdsNo);
	public GoodsVO read_goods_no_join(Long gdsNo);

	//상품상세보기
	public GoodsListAttachVO read_goods_detail(Long gdsNo);

	public int delete_goods(Long gdsNo);
	public int update_goods(GoodsVO goods);


	//화면에 데이터 띄우기
	public List<GoodsListAttachVO> getList_best();
	public List<GoodsListAttachVO> getList_new();
	public List<GoodsListAttachVO> getList_man();
	public List<GoodsListAttachVO> getList_woman();
	public List<GoodsListAttachVO> getList_unisex();


	//페이징
//	public List<GoodsListAttachVO> getListWithPaging_best(Criteria cri);
//	public List<GoodsListAttachVO> getListWithPaging_new(Criteria cri);
//	public List<GoodsListAttachVO> getListWithPaging_man(Criteria cri);
//	public List<GoodsListAttachVO> getListWithPaging_woman(Criteria cri);
//	public List<GoodsListAttachVO> getListWithPaging_unisex(Criteria cri);
	public List<GoodsListAttachVO> getListWithPaging_best(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getListWithPaging_new(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getListWithPaging_man(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getListWithPaging_woman(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getListWithPaging_unisex(GoodsCriteria1 cri);

	//데이터 개수 처리
	public int getTotalCount_best(GoodsCriteria1 cri);
	public int getTotalCount_new(GoodsCriteria1 cri);
	public int getTotalCount_man(GoodsCriteria1 cri);
	public int getTotalCount_woman(GoodsCriteria1 cri);
	public int getTotalCount_unisex(GoodsCriteria1 cri);
//	public int getTotalCount_best(Criteria cri);
//	public int getTotalCount_new(Criteria cri);
//	public int getTotalCount_man(Criteria cri);
//	public int getTotalCount_woman(Criteria cri);
//	public int getTotalCount_unisex(Criteria cri);


	//검색페이지 검색 및 페이징 처리
	public List<GoodsListAttachVO> getListWithPaging_search(GoodsCriteria2 cri);
	public int getTotalCount_search(GoodsCriteria2 cri);

	//조회수 처리
	public void gdsViews(Long gdsNo) throws Exception;
}
