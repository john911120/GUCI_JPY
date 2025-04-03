package com.guci.service;

import java.util.List;

import com.guci.domain.Criteria;
import com.guci.domain.NoticeVO;

public interface NoticeService {

	public void register(NoticeVO notice);

	public NoticeVO get(Long noticeNo);

	public boolean modify(NoticeVO notice);

	public boolean remove(Long noticeNo);

	public List<NoticeVO> getList(Criteria cri);

	public int getTotal(Criteria cri);
}

