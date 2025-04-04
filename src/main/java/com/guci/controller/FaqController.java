package com.guci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guci.domain.FaqVO;
import com.guci.service.FaqService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/faq")
@AllArgsConstructor
public class FaqController {
	
	@Autowired
	private FaqService service;

	// すべてのFAQ記事を取得します 
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList());
	}

	/*
	この register() メソッドは、新規登録フォームを表示するだけの役割で、処理ロジックは含まれていません。
	Spring MVCのビュー解決規則により、/faq/register.jsp が自動的に表示されます。 
	*/
	@GetMapping("/register")
	public void register() {}
	
	// 登録と同時に生成された主キー（faqNo）を取得します
	@PostMapping("/register")
	public String register(FaqVO faq, RedirectAttributes rttr) {
		log.info("register : "+faq);
		service.register(faq);
		rttr.addFlashAttribute("result",faq.getFaqNo());
		return "redirect:/faq/list";
	}

	// FAQ記事の詳細を取得します
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("faqNo") Long faqNo, Model model) {
		log.info("/get or modify");
		model.addAttribute("faq", service.get(faqNo));
	}

	// FAQ記事を更新します
	@PostMapping("/modify")
	public String modify(FaqVO faq, RedirectAttributes rttr) {
		log.info("modify:"+faq);
		if(service.modify(faq)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/faq/list";
	}

	// FAQ記事を削除します
	@PostMapping("/remove")
	public String remove(@RequestParam("faqNo") Long faqNo, RedirectAttributes rttr) {
		log.info("remove..."+faqNo);
		if(service.remove(faqNo)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/faq/list";
	}
}
