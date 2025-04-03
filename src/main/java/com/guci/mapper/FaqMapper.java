package com.guci.mapper;

import java.util.List;

import com.guci.domain.FaqVO;

public interface FaqMapper {
	public List<FaqVO> getList();
	public void insert(FaqVO faq);
	public void insertSelectKey(FaqVO faq);
	public FaqVO read(Long faqNo);
	public int delete(Long faqNo);
	public int update(FaqVO faq);
}
