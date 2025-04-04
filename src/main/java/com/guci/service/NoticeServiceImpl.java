package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guci.domain.Criteria;
import com.guci.domain.NoticeVO;
import com.guci.mapper.NoticeMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	/*
	  お知らせの記事を管理する
	  DBへのアクセスはNoticeMapperを通じて行う。
	 */
	
	
	@Autowired
	private NoticeMapper mapper;

	//登録と同時に生成された主キー（noticeNo）を取得
	@Override
	public void register(NoticeVO notice) {
		log.info("register.........."+notice);
		mapper.insertSelectKey(notice);

	}

	//記事詳細の取得
	@Override
	public NoticeVO get(Long noticeNo) {
		log.info("get.........."+noticeNo);
		return mapper.read(noticeNo);
	}
	
	//記事を更新
	@Override
	public boolean modify(NoticeVO notice) {
		log.info("modify.........."+notice);
		return mapper.update(notice) ==1;
	}

	//記事を削除
	@Override
	public boolean remove(Long noticeNo) {
		log.info("remove.........."+noticeNo);
		return mapper.delete(noticeNo)==1;
	}
	
	//ページング付きでお知らせ記事を取得
	@Override
	public List<NoticeVO> getList(Criteria cri) {
		log.info("get List with criteria : "+cri);
		return mapper.getListWithPaging(cri);
	}

	//総記事数を取得（ページングのため）
	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

}
