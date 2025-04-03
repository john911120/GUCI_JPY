package com.guci.service;

import java.util.List;

import com.guci.domain.QuesAttachVO;
import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesVO;

public interface QuesService {
	public void register(QuesVO board); // insert

	public QuesVO get(Long quesNo); // select

	public boolean modify(QuesVO board); // update

	public boolean remove(Long quesNo); // delete

	public List<QuesVO> getList(QuesCriteria cri); // select *

	public int getTotal(QuesCriteria cri); // p323

	// (569) 게시물 조회와 첨부파일을 위한 코드 추가
	public List<QuesAttachVO> getAttachList(Long quesNo);




    }
