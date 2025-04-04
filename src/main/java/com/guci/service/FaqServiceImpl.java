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
	/*
	  FAQ（よくある質問）に関するデータベース操作を担当する
	  DBへのアクセスはFaqMapperを通じて行う。
	 */
	
	@Autowired
	private FaqMapper mapper;

	// すべてのFAQ記事を取得します 
	@Override
	public List<FaqVO> getList() {
		log.info("getList");
		return mapper.getList();
	}
	// 登録と同時に生成された主キー（faqNo）を取得します
	@Override
	public void register(FaqVO faq) {
		log.info("register..."+faq);
		mapper.insertSelectKey(faq);
	}
	// FAQ記事の詳細を取得します
	@Override
	public FaqVO get(Long faqNo) {
		log.info("get..."+faqNo);
		return mapper.read(faqNo);
	}
	// FAQ記事を更新します
	@Override
	public boolean modify(FaqVO faq) {
		log.info("modify..."+faq);
		return mapper.update(faq)==1;
	}
	
	// FAQ記事を削除します
	@Override
	public boolean remove(Long faqNo) {
		log.info("remove..."+faqNo);
		return mapper.delete(faqNo) == 1;
	}

}
