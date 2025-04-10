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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guci.domain.Criteria;
import com.guci.domain.ReviewAttachVO;
import com.guci.domain.ReviewCriteria;
import com.guci.domain.ReviewPageDTO;
import com.guci.domain.ReviewVO;
import com.guci.service.GoodsService;
import com.guci.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/review")
@Controller
@AllArgsConstructor
public class ReviewController {

	// 商品レビュー及び添付ファイルを管理するコントローラー
	@Autowired
	private ReviewService service;
	
	// 商品情報取得用サービス（レビューに商品名などを表示するため）
	@Autowired
	private GoodsService gService;

	// 商品詳細ページ：レビュー一覧 + 商品情報 + ページング情報を表示
	@GetMapping("/product_detail")
	public void product_detail(@RequestParam("gdsNo") Long gdsNo ,ReviewCriteria cri, Model model) throws Exception {
		log.info("product_detail"+cri);
		cri.setGdsNo(gdsNo);
		model.addAttribute("product_detail", service.getListImgWithPaging(cri));
		model.addAttribute("goods",gService.get_goods_detail(gdsNo));
		int total=service.getTotal(cri);
		log.info("total: "+total);
		model.addAttribute("pageMaker",new ReviewPageDTO(cri, total));
	}
	
	// レビュー登録処理（添付ファイル情報も含む）
	@GetMapping("/register")
	public void register(@RequestParam("gdsNo") Long gdsNo, Model model) throws Exception {
		model.addAttribute("goods", gService.get_goods_detail(gdsNo));
	}
	@PostMapping("/register")
	public String register(@RequestParam("gdsNo") Long gdsNo,ReviewVO review, MultipartFile file) throws Exception {
		log.info("===============");
		log.info("register : "+review);

		if(review.getAttachList() != null) {
			review.getAttachList().forEach(attach -> log.info(attach));
		}
		log.info("================");
		service.register(review);
		service.getAttachList(review.getRevNo());

		return "redirect:/review/product_detail?gdsNo="+gdsNo;
	}

	// 添付ファイルリストをJSON形式で取得（レビュー詳細表示などに使用）
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<ReviewAttachVO>> getAttachList(Long revNo){
		log.info("getAttachList" + revNo);

		return new ResponseEntity<>(service.getAttachList(revNo), HttpStatus.OK);
	}

	@GetMapping("/modify")
	public void modify(@RequestParam("revNo") Long revNo, @ModelAttribute("cri") Criteria cri ,Model model) {
		log.info("/get or modify");

		model.addAttribute("review",service.get(revNo));
	}

	// レビュー修正処理
	@PostMapping("/modify")
	public String modify(ReviewVO review,@RequestParam("gdsNo") Long gdsNo, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {

		log.info("modify : " + review);

		if(service.modify(review)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/review/product_detail?gdsNo="+gdsNo+"&" + cri.getListLink(); // URL
	}
	
	// レビュー削除時、ファイルも一緒に削除する処理
	@PostMapping("/remove")
	public String remove(@RequestParam("revNo") Long revNo,@RequestParam("gdsNo") Long gdsNo, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove...."+revNo);
		List<ReviewAttachVO> attachList = service.getAttachList(revNo);

		if(service.remove(revNo)) {
			deleteFiles(attachList);
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/review/product_detail?gdsNo="+gdsNo+"&" + cri.getListLink();
	}

	private void deleteFiles(List<ReviewAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}

		log.info("delete attach files...........");
		log.info(attachList);

		attachList.forEach(attach -> {

			try {
				Path file = Paths.get("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/review/img/"+attach.getUploadPath()+"\\" + attach.getUuid()+"_"+attach.getFileName());
				Files.deleteIfExists(file);
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/review/img/"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					Files.delete(thumbNail);
				}
			} catch (Exception e) {
				log.error("delete file error : " + e.getMessage());
			}
		});
	}
}
