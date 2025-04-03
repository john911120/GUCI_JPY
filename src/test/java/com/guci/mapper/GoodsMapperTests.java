package com.guci.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guci.domain.GoodsCriteria1;
import com.guci.domain.GoodsListAttachVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class GoodsMapperTests {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private GoodsMapper mapper;


//	@Test
//	public void testGetList() {
//		mapper.getListWithPaging_new(new Criteria(1,5)).forEach(goods -> log.info(goods));
//	}
//
//	@Test
//	public void  testInsert() {
//		GoodsVO goods = new GoodsVO();
//		goods.setGdsName("商品1");
//		goods.setGdsPrice(5000L);
//		goods.setGdsDes("商品説明1");
//		goods.setGdsSize("S");
//		goods.setCateCode("101");
//
//		mapper.insert_goods(goods);
//		log.info(goods);
//	}
//
//	@Test
//	public void  testInsertSelectKey() {
//		GoodsVO goods = new GoodsVO();
//		goods.setGdsName("商品2");
//		goods.setGdsPrice(15000L);
//		goods.setGdsDes("商品説明2");
//		goods.setGdsSize("L");
//		goods.setCateCode("102");
//
//		mapper.insertSelectKey_goods(goods);
//		log.info(goods);
//	}
//
//	@Test
//	public void testRead() {
//		GoodsListVO goods = mapper.read_goods(1L);
//		log.info(goods);
//	}
//
//	@Test
//	public void testDelete() {
//		log.info("DELETE COUNT : "+mapper.delete_goods(11L));
//	}
//
//	@Test
//	public void testUpdate() {
//		GoodsVO goods = new GoodsVO();
//		goods.setGdsNo(12L);
//		goods.setGdsName("修正商品1");
//		goods.setGdsPrice(25000L);
//		goods.setGdsDes("修正コンテンツ1");
//		goods.setGdsSize("M");
//		goods.setCateCode("201");
//
//		int count = mapper.update_goods(goods);
//		log.info("UPDATE COUNT : "+count);
//	}

//	@Test
//	public void testGetImg() {
//		mapper.getList_goods_best().forEach(goods -> log.info(goods));
//	}

//	@Test
//	public void testPaging() {
//		Criteria cri = new Criteria();
//		cri.setPageNum(1);
//		cri.setAmount(5);
//		List<GoodsListAttachVO> list = mapper.getListWithPaging_new(cri);
//		list.forEach(goods -> log.info(goods));
//	}
//
	@Test
	public void testSearch() {
		GoodsCriteria1 cri = new GoodsCriteria1();
		cri.setType("男性");
		List<GoodsListAttachVO> list = mapper.getListWithPaging_best(cri);
		list.forEach(goods -> log.info(goods));
	}

}
