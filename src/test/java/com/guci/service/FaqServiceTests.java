package com.guci.service;

import static org.junit.Assert.assertNotNull;

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
public class FaqServiceTests {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private FaqService faqService;

	@Test
	public void testExist() {
		log.info(faqService);
		assertNotNull(faqService);
	}

	@Test
	public void testGet() {
		log.info(faqService.get(1L));
	}

	@Test
	public void testGetList() {
		faqService.getList().forEach(faq -> log.info(faq));
	}

	@Test
	public void testDelete() {
		log.info("REMOVE RESULT: "+faqService.remove(3L));
	}

	@Test
	public void testUpdate() {
		FaqVO faq = faqService.get(4L);

		if(faq == null) {
			return;
		}

		faq.setFaqTit("タイトル 修正");
		log.info("MODIFY RESULT : "+faqService.modify(faq));
	}

}
