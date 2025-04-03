package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.guci.domain.CategoryVO;
import com.guci.domain.GoodsAttachVO;
import com.guci.domain.GoodsCriteria1;
import com.guci.domain.GoodsCriteria2;
import com.guci.domain.GoodsListAttachVO;
import com.guci.domain.GoodsListVO;
import com.guci.domain.GoodsVO;
import com.guci.mapper.GoodsAttachMapper;
import com.guci.mapper.GoodsMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class GoodsServiceImpl implements GoodsService {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private GoodsMapper mapper;

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private GoodsAttachMapper attachMapper;

	@Transactional
	@Override
	public void register_goods(GoodsVO goods) {
		log.info("register..." + goods);
		mapper.insertSelectKey_goods(goods);

		if(goods.getAttachList() == null || goods.getAttachList().size() <= 0) {
			return;
		}

		goods.getAttachList().forEach(attach ->{
			attach.setGdsNo(goods.getGdsNo());
			attach.setGdsName(goods.getGdsName());
			log.info(attach);
			attachMapper.insert(attach);
		});
	}

	@Override
	public GoodsListVO get_goods(Long gdsNo) {
		log.info("get..."+gdsNo);
		return mapper.read_goods(gdsNo);
	}

	@Override
	public GoodsVO get_goods_no_join(Long gdsNo) {
		log.info("get..."+gdsNo);
		return mapper.read_goods_no_join(gdsNo);
	}

	@Transactional
	@Override
	public boolean modify_goods(GoodsVO goods) {
		log.info("modify..."+goods);
		attachMapper.deleteAll(goods.getGdsNo());
		boolean modifyResult = mapper.update_goods(goods)==1;

		if(modifyResult && goods.getAttachList() != null && goods.getAttachList().size()>0) {
			goods.getAttachList().forEach(attach->{
				attach.setGdsNo(goods.getGdsNo());
				attachMapper.insert(attach);
			});
		}

		return modifyResult;
	}

	@Transactional
	@Override
	public boolean remove_goods(Long gdsNo) {
		log.info("remove..."+gdsNo);
		attachMapper.deleteAll(gdsNo);
		return mapper.delete_goods(gdsNo)==1;
	}

	@Override
	public List<GoodsListVO> getList_goods() {
		log.info("getList...");
		return mapper.getList_goods();
	}

	@Override
	public List<CategoryVO> category_goods() {
		return mapper.category_goods();
	}

	@Override
	public List<GoodsAttachVO> getAttachList(Long gdsNo) {
		log.info("get Attach list by gdsNo"+gdsNo);
		return attachMapper.findByGdsNo(gdsNo);
	}


	//페이지에 데이터 넣기
	@Override
	public List<GoodsListAttachVO> getList_best(GoodsCriteria1 cri) {
		return mapper.getListWithPaging_best(cri);
	}

	@Override
	public List<GoodsListAttachVO> getList_new(GoodsCriteria1 cri) {
		return mapper.getListWithPaging_new(cri);
	}

	@Override
	public List<GoodsListAttachVO> getList_man(GoodsCriteria1 cri) {
		return mapper.getListWithPaging_man(cri);
	}

	@Override
	public List<GoodsListAttachVO> getList_woman(GoodsCriteria1 cri) {
		return mapper.getListWithPaging_woman(cri);
	}

	@Override
	public List<GoodsListAttachVO> getList_unisex(GoodsCriteria1 cri) {
		return mapper.getListWithPaging_unisex(cri);
	}
//	@Override
//	public List<GoodsListAttachVO> getList_best(Criteria cri) {
//		return mapper.getListWithPaging_best(cri);
//	}
//
//	@Override
//	public List<GoodsListAttachVO> getList_new(Criteria cri) {
//		return mapper.getListWithPaging_new(cri);
//	}
//
//	@Override
//	public List<GoodsListAttachVO> getList_man(Criteria cri) {
//		return mapper.getListWithPaging_man(cri);
//	}
//
//	@Override
//	public List<GoodsListAttachVO> getList_woman(Criteria cri) {
//		return mapper.getListWithPaging_woman(cri);
//	}
//
//	@Override
//	public List<GoodsListAttachVO> getList_unisex(Criteria cri) {
//		return mapper.getListWithPaging_unisex(cri);
//	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public GoodsListAttachVO get_goods_detail(Long gdsNo) throws Exception {
		mapper.gdsViews(gdsNo);
		return mapper.read_goods_detail(gdsNo);	}


	//페이지 수 처리
	@Override
	public int getTotal_best(GoodsCriteria1 cri) {
		return mapper.getTotalCount_best(cri);
	}

	@Override
	public int getTotal_new(GoodsCriteria1 cri) {
		return mapper.getTotalCount_new(cri);
	}

	@Override
	public int getTotal_man(GoodsCriteria1 cri) {
		return mapper.getTotalCount_man(cri);
	}

	@Override
	public int getTotal_woman(GoodsCriteria1 cri) {
		return mapper.getTotalCount_woman(cri);
	}

	@Override
	public int getTotal_unisex(GoodsCriteria1 cri) {
		return mapper.getTotalCount_unisex(cri);
	}

//	@Override
//	public int getTotal_best(Criteria cri) {
//		return mapper.getTotalCount_best(cri);
//	}
//
//	@Override
//	public int getTotal_new(Criteria cri) {
//		return mapper.getTotalCount_new(cri);
//	}
//
//	@Override
//	public int getTotal_man(Criteria cri) {
//		return mapper.getTotalCount_man(cri);
//	}
//
//	@Override
//	public int getTotal_woman(Criteria cri) {
//		return mapper.getTotalCount_woman(cri);
//	}
//
//	@Override
//	public int getTotal_unisex(Criteria cri) {
//		return mapper.getTotalCount_unisex(cri);
//	}


	//검색 처리
	@Override
	public List<GoodsListAttachVO> getList_search(GoodsCriteria2 cri) {
		return mapper.getListWithPaging_search(cri);
	}

	@Override
	public int getTotal_search(GoodsCriteria2 cri) {
		return mapper.getTotalCount_search(cri);
	}

}
