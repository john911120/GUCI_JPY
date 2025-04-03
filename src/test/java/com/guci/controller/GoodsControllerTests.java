package com.guci.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
@WebAppConfiguration
public class GoodsControllerTests {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

//	@Test
//	public void test() throws Exception {
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/admin/goods/list"))
//				.andReturn().getModelAndView().getModelMap());
//	}
//
//	@Test
//	public void testRegister() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/admin/goods/register")
//				.param("gdsName", "상품명3")
//				.param("gdsPrice", "30000")
//				.param("gdsDes","설명3")
//				.param("gdsSize","M")
//				.param("cateCode","202")
//				).andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
//
//	@Test
//	public void testGet() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/admin/goods/get")
//				.param("gdsNo", "1"))
//				.andReturn().getModelAndView().getModelMap());
//	}
//
//	@Test
//	public void testModify() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/admin/goods/modify")
//				.param("gdsNo", "12")
//				.param("gdsName", "최종")
//				.param("gdsPrice", "10000")
//				.param("gdsDes", "최종 설명")
//				.param("gdsSize", "L")
//				.param("cateCode", "101"))
//				.andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
//
//	@Test
//	public void testRemove() throws Exception{
//		String resultPagne = mockMvc.perform(MockMvcRequestBuilders.post("/admin/goods/remove")
//				.param("gdsNo", "13")
//				).andReturn().getModelAndView().getViewName();
//		log.info(resultPagne);
//	}
//
//	@Test
//	public void testListGoodsImg() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/bestProduct"))
//				.andReturn().getModelAndView().getModelMap());
//	}
//	@Test
//	public void testGet() throws Exception {
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/get")
//				.param("gdsNo", "107")) //이 값이 @RequestParam("bno)로 들어감
//				.andReturn().getModelAndView().getModelMap());
//	}

	@Test
	public void testListPaging() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/bestProduct")
				.param("pageNum", "2")
				.param("amount", "5"))
				.andReturn().getModelAndView().getModelMap());
	}
}
