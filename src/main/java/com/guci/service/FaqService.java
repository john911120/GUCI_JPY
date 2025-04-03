package com.guci.service;

import java.util.List;

import com.guci.domain.FaqVO;

public interface FaqService {
	public List<FaqVO> getList();
	public void register(FaqVO faq);
	public FaqVO get(Long faqNo);
	public boolean modify(FaqVO faq);
	public boolean remove(Long faqNo);
}
