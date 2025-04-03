package com.guci.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guci.domain.CategoryVO;
import com.guci.domain.GoodsAttachVO;
import com.guci.domain.GoodsVO;
import com.guci.service.GoodsService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;

@Controller
@Log4j
@RequestMapping("/admin/goods")
@AllArgsConstructor
public class GoodsController {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private GoodsService service;

	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList_goods());
	}

	@GetMapping("/register")
	public void register(Model model) {
		log.info("get goods register");

		List<CategoryVO> category = null;
		category = service.category_goods();
		model.addAttribute("category", JSONArray.fromObject(category));
	}

	@PostMapping("/register")
	public String register(GoodsVO goods, RedirectAttributes rttr) {

		log.info("===================");
		log.info("register : "+goods);


		if(goods.getAttachList() != null) {
			goods.getAttachList().forEach(attach -> log.info(attach));
		}
		log.info("====================");
		service.register_goods(goods); //데이터가 board객체로 넘어와서 DB에 등록됨
		service.getAttachList(goods.getGdsNo());
	    //한번만 등록하고 사라짐, redirect시 데이터 전달 가능
		rttr.addFlashAttribute("result", goods.getGdsNo());
		return "redirect:/admin/goods/list"; //목록 페이지로 돌아감
	}

	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("gdsNo") long gdsNo, Model model) {
		log.info("/get or modify");
		List<CategoryVO> category = null;
		category = service.category_goods();
		model.addAttribute("category", JSONArray.fromObject(category));

		model.addAttribute("goods", service.get_goods(gdsNo));
	}

	@PostMapping("/modify")
	public String modify(GoodsVO goods, RedirectAttributes rttr) {
		log.info("modify : "+goods);
		if(service.modify_goods(goods)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/admin/goods/list";
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("gdsNo") Long gdsNo, RedirectAttributes rttr) {
		log.info("remove..."+gdsNo);
		List<GoodsAttachVO> attachList = service.getAttachList(gdsNo);
		if(service.remove_goods(gdsNo)) {
			deleteFiles(attachList);
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/admin/goods/list";
	}

	private void deleteFiles(List<GoodsAttachVO> attachList) {
		if(attachList == null || attachList.size()==0) {
			return;
		}
		log.info("delete attach files....");
		log.info(attachList);

		attachList.forEach(attach -> {
			try {
				Path file = Paths.get("+"
						+ ""+attach.getUploadPath()+"/"+attach.getUuid()+"_"+attach.getFileName());
				Files.deleteIfExists(file);
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/admin/img/"+attach.getUploadPath()+"/s_"+attach.getUuid()+"_"+attach.getFileName());
					Files.delete(thumbNail);
				}
			} catch (Exception e) {
				log.error("delete file error"+e.getMessage());
			}
		});
	}

	@GetMapping(value="/getAttachList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<GoodsAttachVO>> getAttachList(Long gdsNo){
		log.info("getAttachList "+gdsNo);
		return new ResponseEntity<>(service.getAttachList(gdsNo), HttpStatus.OK);
	}
}