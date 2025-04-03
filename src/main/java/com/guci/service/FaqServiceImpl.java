package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guci.domain.FaqVO;
import com.guci.mapper.FaqMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class FaqServiceImpl implements FaqService {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private FaqMapper mapper;

	@Override
	public List<FaqVO> getList() {
		log.info("getList");
		return mapper.getList();
	}

	@Override
	public void register(FaqVO faq) {
		log.info("register..."+faq);
		mapper.insertSelectKey(faq);
	}

	@Override
	public FaqVO get(Long faqNo) {
		log.info("get..."+faqNo);
		return mapper.read(faqNo);
	}

	@Override
	public boolean modify(FaqVO faq) {
		log.info("modify..."+faq);
		return mapper.update(faq)==1;
	}

	@Override
	public boolean remove(Long faqNo) {
		log.info("remove..."+faqNo);
		return mapper.delete(faqNo) == 1;
	}

}
