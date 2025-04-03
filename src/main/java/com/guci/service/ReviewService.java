  package com.guci.service;

import java.util.List;

import com.guci.domain.ReviewAttachVO;
import com.guci.domain.ReviewCriteria;
import com.guci.domain.ReviewImgUserVO;
import com.guci.domain.ReviewVO;

public interface ReviewService {

	public void register(ReviewVO review);

	public ReviewVO get(Long revNo);

	public boolean modify(ReviewVO review);

	public boolean remove(Long revNo);

//	public List<ReviewVO> getList(Criteria cri);
	public List<ReviewVO> getList(Long gdsNo);

	public int getTotal(ReviewCriteria cri);

	public List<ReviewAttachVO> getAttachList(Long revNo);

	public List<ReviewImgUserVO> getListImgWithPaging(ReviewCriteria cri);


}
