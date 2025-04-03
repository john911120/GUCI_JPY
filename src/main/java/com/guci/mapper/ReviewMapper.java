package com.guci.mapper;

import java.util.List;

import com.guci.domain.Criteria;
import com.guci.domain.ReviewCriteria;
import com.guci.domain.ReviewImgUserVO;

//import org.apache.ibatis.annotations.Select;

import com.guci.domain.ReviewVO;

public interface ReviewMapper {

	//@Select("select * from review where revNo>0")
//	public List<ReviewVO> getList();
	public List<ReviewVO> getList(Long gdsNo);

	public List<ReviewVO> getListWithPaging(Criteria cri);

	public void insert(ReviewVO review);

	public void insertSelectKey(ReviewVO review);

	public ReviewVO read(Long revNo);

	public int delete(Long revNo);

	public int update(ReviewVO review);

	public int getTotalCount(ReviewCriteria cri);

	public List<ReviewImgUserVO> getListImgWithPaging(ReviewCriteria cri);


}
