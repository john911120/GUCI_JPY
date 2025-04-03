package com.guci.service;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class GoodsServiceTests {
	
	private static final Logger log = LoggerFactory.getLogger(GoodsServiceTests.class);

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private GoodsService service;

//	@Test
//	public void testExtist() {
//		log.info(service);
//		assertNotNull(service);
//	}
//
//	@Test
//	public void testGet() {
//		log.info(service.get_goods(1L));
//	}
//
//	@Test
//	public void testGetList() {
//		service.getList_goods().forEach(goods -> log.info(goods));
//	}
//
//	@Test
//	public void testDelete() {
//		log.info("REMOVE RESULT : "+service.remove_goods(4L));
//	}
//
//	@Test
//	public void testUpdate() {
//		GoodsVO goods = service.get_goods_no_join(12L);
//		if(goods == null) return;
//
//		goods.setGdsName("수정수정");
//		log.info("MODIFY RESULT : "+service.modify_goods(goods));
//	}
//
//	@Test
//	public void testGetList() {
//		service.getList_goods_unisex(new Criteria(1,5)).forEach(goods -> log.info(goods));
//	}
}
