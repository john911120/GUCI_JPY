package com.guci.service;

import java.util.List;

import com.guci.domain.FaqVO;

public interface FaqService {
	/*
	   FAQの記事を管理するインターフェース
	  各メソッドはコントローラーから呼び出され、ビジネスロジックを担当する。
	 */
	/*
	すべてのFAQ記事を取得します 
	public List<FaqVO> getList();
	
	登録と同時に生成された主キー（faqNo）を取得します
	public void register(FaqVO faq);
	
	FAQ記事の詳細を取得します
	public FaqVO get(Long faqNo);
	
	FAQ記事を更新します
	public boolean modify(FaqVO faq);
	
	FAQ記事を削除します
	public boolean remove(Long faqNo);
	
	*/
	public List<FaqVO> getList();
	public void register(FaqVO faq);
	public FaqVO get(Long faqNo);
	public boolean modify(FaqVO faq);
	public boolean remove(Long faqNo);
}
