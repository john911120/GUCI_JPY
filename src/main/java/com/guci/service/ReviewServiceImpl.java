package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guci.domain.ReviewAttachVO;
import com.guci.domain.ReviewCriteria;
import com.guci.domain.ReviewImgUserVO;
import com.guci.domain.ReviewVO;
import com.guci.mapper.ReviewAttachMapper;
import com.guci.mapper.ReviewMapper;

import lombok.extern.log4j.Log4j;

/*
	商品のレビューを関するデータベース操作を担当する
	DBへのアクセスはReviewMapper, ReviewAttachMapperを通じて行う。
*/
/*
	商品のレビューを管理するインターフェース
	各メソッドはコントローラーから呼び出され、ビジネスロジックを担当する。
*/ 
@Log4j
@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewMapper mapper;

	@Autowired
	private ReviewAttachMapper attachMapper;
	
	// レビュー登録処理（添付ファイル情報も含む）
	@Override
	public void register(ReviewVO review) {
		log.info("register...."+review);
		mapper.insertSelectKey(review);

		if(review.getAttachList() == null || review.getAttachList().size() <=0) {
			return;
		}

		review.getAttachList().forEach(attach -> {
			attach.setRevNo(review.getRevNo());
			attachMapper.insert(attach);
		});
	}

	// レビュー記事をレビュー番号で取得する
	@Override
	public ReviewVO get(Long revNo) {
		log.info("get....."+revNo);
		return mapper.read(revNo);
	}

	// レビューを修正する
	@Override
	public boolean modify(ReviewVO review) {
		log.info("modify....."+review);
		attachMapper.deleteAll(review.getRevNo());
		boolean modifyResult = mapper.update(review)==1;

		if(modifyResult && review.getAttachList() != null && review.getAttachList().size() >0) {
			review.getAttachList().forEach(attach ->{
				attach.setRevNo(review.getRevNo());
				attachMapper.insert(attach);
			});
		}

		return modifyResult;
	}

	// レビュー削除時、添付ファイルも同時に削除する
	@Transactional
	@Override
	public boolean remove(Long revNo) {
		log.info("remove..."+revNo);
		attachMapper.deleteAll(revNo);

		return mapper.delete(revNo)==1;
	}

	// 商品に対するレビュー一覧を取得する
	@Override
	public List<ReviewVO> getList(Long gdsNo) {
		log.info("get List :"+gdsNo);
		return mapper.getList(gdsNo);
	}

	// 商品ごとのレビュー総件数を取得する（ページング対応）
	@Override
	public int getTotal(ReviewCriteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	// 指定したレビューの添付ファイル一覧をJSON形式で取得する
	@Override
	public List<ReviewAttachVO> getAttachList(Long revNo) {
		return attachMapper.findByRevNo(revNo);
	}

	// 商品詳細ページで表示するレビューと添付画像をページング付きで取得する
	@Override
	public List<ReviewImgUserVO> getListImgWithPaging(ReviewCriteria cri) {
		return mapper.getListImgWithPaging( cri);
	}
}
