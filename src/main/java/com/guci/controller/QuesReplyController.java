package com.guci.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesReplyPageDTO;
import com.guci.domain.QuesReplyVO;
import com.guci.service.QuesReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/*
  Q&Aのコメント（返信）機能をRESTful形式で提供するコントローラー
  - JSONデータを受け取り、登録・取得・修正・削除を処理する
 */

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class QuesReplyController {

	// Q&Aコメントサービスを注入
	private QuesReplyService service;


	/*
	  コメントを新規登録する
	  @param vo コメント内容（JSON）
	  @return 成功：200 OK / 失敗：500 INTERNAL_SERVER_ERROR
	 */
	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody QuesReplyVO vo) {
		log.info("QuesReplyVO: " + vo);
		int insertCount = service.register(vo);
		log.info("Reply INSERT COUNT: " + insertCount);
		return insertCount == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	  特定のQ&A記事に対するコメント一覧（ページング対応）
	  @param page ページ番号
	  @param quesNo Q&A記事番号
	  @return コメントのリストとページ情報を含むDTO
	 */
	@GetMapping(value = "/pages/{quesNo}/{page}",
			produces = {
				MediaType.APPLICATION_XML_VALUE,
				MediaType.APPLICATION_JSON_UTF8_VALUE
			})
	public ResponseEntity<QuesReplyPageDTO> getList(
			@PathVariable("page") int page,
			@PathVariable("quesNo") Long quesNo){
		QuesCriteria cri = new QuesCriteria(page, 10);
		log.info("get Reply List quesNo : " + quesNo);
		log.info("cri:" + cri);
		return new ResponseEntity<>(service.getListPage(cri, quesNo), HttpStatus.OK);
	}

	/*
	  コメント1件を取得する（ID指定）
	 */
	@GetMapping(value = "/{rno}",
			produces = {
				MediaType.APPLICATION_XML_VALUE,
				MediaType.APPLICATION_JSON_UTF8_VALUE
			})
	public ResponseEntity<QuesReplyVO> get(@PathVariable("rno") Long rno){
		log.info("get : " + rno);
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}

	/*
	  コメントを削除する
	  @param rno コメント番号
	  @return 成功：200 OK / 失敗：500 INTERNAL_SERVER_ERROR
	 */
	@DeleteMapping(value = "/{rno}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("remove : " + rno);
		return service.remove(rno) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	  コメントを修正する（PUTまたはPATCH対応）
	  @param vo 修正内容（JSON）
	  @param rno コメント番号（URLから取得）
	 */
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
					value="/{rno}",
					consumes = "application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
				@RequestBody QuesReplyVO vo,
				@PathVariable("rno") Long rno){
		vo.setRno(rno);
		log.info("replyNo : " + rno);
		log.info("modify : " + vo);
		return service.modify(vo) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
