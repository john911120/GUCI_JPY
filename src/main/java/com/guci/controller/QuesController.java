package com.guci.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guci.domain.QuesAttachVO;
import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesPageDTO;
import com.guci.domain.QuesVO;
import com.guci.service.QuesService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/question/*")
@AllArgsConstructor
public class QuesController {

	private QuesService service;


	@GetMapping("/list")
	public void list(QuesCriteria cri, Model model) {
		log.info("list" + cri);
		model.addAttribute("list", service.getList(cri));

		int total = service.getTotal(cri);

		log.info("total : " + total);

		model.addAttribute("pageMaker", new QuesPageDTO(cri, total));
	}


	@PostMapping("/register")
	public String register(QuesVO ques, RedirectAttributes rttr) {

		log.info("=========================================");
		log.info("register : " + ques);
		if(ques.getAttachList() != null) {
				ques.getAttachList().forEach(attach -> log.info(attach));
		}
		log.info("=========================================");
		 service.register(ques);

		 rttr.addFlashAttribute("result", ques.getQuesNo()); // model

		return "redirect:/question/list"; // URL
	}

	// (570) quesController 변경 화면 처리
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<QuesAttachVO>> getAttachList(Long quesNo){
		log.info("getAttachList" + quesNo);

		return new ResponseEntity<>(service.getAttachList(quesNo), HttpStatus.OK);
	}

	// (239)

	@GetMapping({"/get","modify"})
	public void get(@RequestParam("quesNo") Long quesNo, @ModelAttribute("cri") QuesCriteria cri, Model model) {
		log.info("/get or modify");
		model.addAttribute("ques", service.get(quesNo));
	}


	@GetMapping("/register")
	public void register() {

	}

	@PostMapping("/modify")
	public String modify(QuesVO ques, @ModelAttribute("cri") QuesCriteria cri, RedirectAttributes rttr) {

		log.info("modify : " + ques);

		if(service.modify(ques)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/question/list" + cri.getListLink(); // URL
	}


	@PostMapping("/remove")
	public String remove(@RequestParam("quesNo") Long quesNo, QuesCriteria cri, RedirectAttributes rttr) {

		log.info("remove : " + quesNo);

		List<QuesAttachVO> attachList = service.getAttachList(quesNo);

		if(service.remove(quesNo)) {


			deleteFiles(attachList);

			rttr.addFlashAttribute("result", "success");
		}


		return "redirect:/question/list" + cri.getListLink(); // URL
	}

	private void deleteFiles(List<QuesAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}

		log.info("delete attach files...........");
		log.info(attachList);

		attachList.forEach(attach -> {

			try {
				Path file = Paths.get("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/question/"+attach.getUploadPath()+"\\" + attach.getUuid()+"_"+attach.getFileName());
				Files.deleteIfExists(file);
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/question/"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					Files.delete(thumbNail);
				}
			} catch (Exception e) {
				log.error("delete file error : " + e.getMessage());
			}
		});
	}


}
