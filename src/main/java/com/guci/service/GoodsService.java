package com.guci.service;

import java.util.List;

import com.guci.domain.CategoryVO;
import com.guci.domain.GoodsAttachVO;
import com.guci.domain.GoodsCriteria1;
import com.guci.domain.GoodsCriteria2;
import com.guci.domain.GoodsListAttachVO;
import com.guci.domain.GoodsListVO;
import com.guci.domain.GoodsVO;

public interface GoodsService {
	public List<CategoryVO> category_goods();

	public void register_goods(GoodsVO goods);
	public GoodsListVO get_goods(Long gdsNo);
	public GoodsVO get_goods_no_join(Long gdsNo);
	public boolean modify_goods(GoodsVO goods);
	public boolean remove_goods(Long gdsNo);
	public List<GoodsListVO> getList_goods();

	//상세조회 페이지
	public GoodsListAttachVO get_goods_detail(Long gdsNo) throws Exception;

	public List<GoodsAttachVO> getAttachList(Long gdsNo);


	//베스트, 신상, 남자, 여자, 공용탭에 해당 상품들이 들어오도록 코딩
	public List<GoodsListAttachVO> getList_best(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getList_new(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getList_man(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getList_woman(GoodsCriteria1 cri);
	public List<GoodsListAttachVO> getList_unisex(GoodsCriteria1 cri);
//	public List<GoodsListAttachVO> getList_best(Criteria cri);
//	public List<GoodsListAttachVO> getList_new(Criteria cri);
//	public List<GoodsListAttachVO> getList_man(Criteria cri);
//	public List<GoodsListAttachVO> getList_woman(Criteria cri);
//	public List<GoodsListAttachVO> getList_unisex(Criteria cri);

	//전체 개수 구하기
	public int getTotal_best(GoodsCriteria1 cri);
	public int getTotal_new(GoodsCriteria1 cri);
	public int getTotal_man(GoodsCriteria1 cri);
	public int getTotal_woman(GoodsCriteria1 cri);
	public int getTotal_unisex(GoodsCriteria1 cri);
//	public int getTotal_best(Criteria cri);
//	public int getTotal_new(Criteria cri);
//	public int getTotal_man(Criteria cri);
//	public int getTotal_woman(Criteria cri);
//	public int getTotal_unisex(Criteria cri);


	//검색페이지 처리
	public List<GoodsListAttachVO> getList_search(GoodsCriteria2 cri);
	public int getTotal_search(GoodsCriteria2 cri);
}
