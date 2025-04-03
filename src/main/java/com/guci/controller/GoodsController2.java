package com.guci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.guci.domain.GoodsCriteria1;
import com.guci.domain.GoodsCriteria2;
import com.guci.domain.GoodsPageDTO1;
import com.guci.domain.GoodsPageDTO2;
import com.guci.service.GoodsService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class GoodsController2 {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private GoodsService service;


	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(GoodsCriteria1 cri, Model model) {
		log.info("main page goods");
		return "index";
	}

	@RequestMapping(value="/bestProduct",method=RequestMethod.GET)
	public void bestProduct(GoodsCriteria1 cri, Model model) {
		log.info("best page goods");
		model.addAttribute("bestProduct",service.getList_best(cri));
		int total = service.getTotal_best(cri);
		model.addAttribute("pageMaker1", new GoodsPageDTO1(cri,total));
	}

	@RequestMapping(value="/newProduct",method=RequestMethod.GET)
	public void newProduct(GoodsCriteria1 cri, Model model) {
		log.info("new page goods");
		model.addAttribute("newProduct",service.getList_new(cri));
		int total = service.getTotal_new(cri);
		model.addAttribute("pageMaker2", new GoodsPageDTO1(cri,total));
	}

	@RequestMapping(value="/manProduct",method=RequestMethod.GET)
	public void manProduct(GoodsCriteria1 cri, Model model) {
		log.info("man page goods");
		model.addAttribute("manProduct",service.getList_man(cri));
		int total = service.getTotal_man(cri);
		model.addAttribute("pageMaker3", new GoodsPageDTO1(cri,total));
	}

	@RequestMapping(value="/womanProduct",method=RequestMethod.GET)
	public void womanProduct(GoodsCriteria1 cri, Model model) {
		log.info("woman page goods");
		model.addAttribute("womanProduct",service.getList_woman(cri));
		int total = service.getTotal_woman(cri);
		model.addAttribute("pageMaker4", new GoodsPageDTO1(cri,total));
	}

	@RequestMapping(value="/unisexProduct",method=RequestMethod.GET)
	public void unisexProduct(GoodsCriteria1 cri, Model model) {
		log.info("unisex page goods");
		model.addAttribute("unisexProduct",service.getList_unisex(cri));
		int total = service.getTotal_unisex(cri);
		model.addAttribute("pageMaker5", new GoodsPageDTO1(cri,total));
	}
//	@RequestMapping(value="/bestProduct",method=RequestMethod.GET)
//	public void bestProduct(GoodsCriteria1 cri, Model model) {
//		log.info("best page goods");
//		model.addAttribute("bestProduct",service.getList_best(cri));
//		int total = service.getTotal_best(cri);
//		model.addAttribute("pageMaker1", new PageDTO(cri,total));
//	}
//
//	@RequestMapping(value="/newProduct",method=RequestMethod.GET)
//	public void newProduct(Criteria cri, Model model) {
//		log.info("new page goods");
//		model.addAttribute("newProduct",service.getList_new(cri));
//		int total = service.getTotal_new(cri);
//		model.addAttribute("pageMaker2", new PageDTO(cri,total));
//	}
//
//	@RequestMapping(value="/manProduct",method=RequestMethod.GET)
//	public void manProduct(Criteria cri, Model model) {
//		log.info("man page goods");
//		model.addAttribute("manProduct",service.getList_man(cri));
//		int total = service.getTotal_man(cri);
//		model.addAttribute("pageMaker3", new PageDTO(cri,total));
//	}
//
//	@RequestMapping(value="/womanProduct",method=RequestMethod.GET)
//	public void womanProduct(Criteria cri, Model model) {
//		log.info("woman page goods");
//		model.addAttribute("womanProduct",service.getList_woman(cri));
//		int total = service.getTotal_woman(cri);
//		model.addAttribute("pageMaker4", new PageDTO(cri,total));
//	}
//
//	@RequestMapping(value="/unisexProduct",method=RequestMethod.GET)
//	public void unisexProduct(Criteria cri, Model model) {
//		log.info("unisex page goods");
//		model.addAttribute("unisexProduct",service.getList_unisex(cri));
//		int total = service.getTotal_unisex(cri);
//		model.addAttribute("pageMaker5", new PageDTO(cri,total));
//	}

//	@RequestMapping(value="/newProduct", method=RequestMethod.GET)
//	public void newProduct(Model model) {
//		log.info("new page goods");
//		model.addAttribute("newProduct", service.getList_new());
//	}
//
//	@RequestMapping(value="/manProduct", method=RequestMethod.GET)
//	public void manProduct( Model model) {
//		log.info("man page goods");
//		model.addAttribute("manProduct", service.getList_man());
//	}
//
//	@RequestMapping(value="/womanProduct", method=RequestMethod.GET)
//	public void womanProduct( Model model) {
//		log.info("man page goods");
//		model.addAttribute("womanProduct", service.getList_woman());
//
//	}
//
//	@RequestMapping(value="/unisexProduct", method=RequestMethod.GET)
//	public void unisexProduct( Model model) {
//		log.info("unisex page goods");
//		model.addAttribute("unisexProduct", service.getList_unisex());
//	}

	//상품 받아오기
	@GetMapping("/product_detail")
	public String product_detail(@RequestParam("gdsNo") Long gdsNo, Model model) throws Exception { //@RequestParam으로 값을 받음
		log.info("/product_detail");
		model.addAttribute("goods",service.get_goods_detail(gdsNo));
		return "product_detail";
	}

	@GetMapping("search_product")
	public void searchProduct(GoodsCriteria2 cri, Model model) {
		log.info("search page goods");
		model.addAttribute("searchProduct",service.getList_search(cri));
		int total = service.getTotal_search(cri);
		model.addAttribute("pageMaker6", new GoodsPageDTO2(cri,total));
	}


}
