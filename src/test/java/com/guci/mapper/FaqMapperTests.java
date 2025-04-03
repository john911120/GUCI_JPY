package com.guci.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guci.domain.FaqVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class FaqMapperTests {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private FaqMapper faqMapper;

//	@Test
//	public void testInsert() {
//		FaqVO faq = new FaqVO();
//		faq.setFaqCate("交換返品");
//		faq.setFaqTit("新しく作成する掲示記事1");
//		faq.setFaqCon("新しく作成する コンテンツ1");
//
//		faqMapper.insert(faq);
//		log.info(faq);
//	}
//
//	@Test
//	public void testInsertSelectKey() {
//		FaqVO faq = new FaqVO();
//		faq.setFaqCate("商品問い合わせ");
//		faq.setFaqTit("新しく作成する 問い合わせ掲示記事1 select key");
//		faq.setFaqCon("新しく作成する コンテンツ1 select key");
//
//		faqMapper.insertSelectKey(faq);
//		log.info(faq);
//	}

//	@Test
//	public void testRead() {
//		FaqVO faq = faqMapper.read(5L);
//		log.info(faq);
//	}

//	@Test
//	public void testDelete() {
//		log.info("DELETE COUNT : " + faqMapper.delete(2L));
//	}

	@Test
	public void testUpdate() {
		FaqVO faq = new FaqVO();
		faq.setFaqNo(5L);
		faq.setFaqCate("他の問い合わせ");
		faq.setFaqTit("修正されたタイトル");
		faq.setFaqCon("修正されたコンテンツ");

		int count = faqMapper.update(faq);
		log.info("UPDATE COUNT: "+count);
	}

	@Test
	public void testgetList() {
		faqMapper.getList().forEach(faq_board -> log.info(faq_board));
	}
}