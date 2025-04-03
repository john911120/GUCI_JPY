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

@Log4j
@Service
public class ReviewServiceImpl implements ReviewService {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private ReviewMapper mapper;

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private ReviewAttachMapper attachMapper;

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

	@Override
	public ReviewVO get(Long revNo) {
		log.info("get....."+revNo);
		return mapper.read(revNo);
	}

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

	@Transactional
	@Override
	public boolean remove(Long revNo) {
		log.info("remove..."+revNo);
		attachMapper.deleteAll(revNo);

		return mapper.delete(revNo)==1;
	}

	@Override
	public List<ReviewVO> getList(Long gdsNo) {
		log.info("get List :"+gdsNo);
		return mapper.getList(gdsNo);
	}
//	@Override
//	public List<ReviewVO> getList(Criteria cri) {
//		log.info("get List With Paging:"+cri);
//		return mapper.getListWithPaging(cri);
//	}

	@Override
	public int getTotal(ReviewCriteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<ReviewAttachVO> getAttachList(Long revNo) {
		return attachMapper.findByRevNo(revNo);
	}

	@Override
	public List<ReviewImgUserVO> getListImgWithPaging(ReviewCriteria cri) {
		return mapper.getListImgWithPaging( cri);
	}
}
