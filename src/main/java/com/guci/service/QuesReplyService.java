package com.guci.service;

import java.util.List;

import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesReplyPageDTO;
import com.guci.domain.QuesReplyVO;

public interface QuesReplyService {

	// (389)
	public int register(QuesReplyVO vo);

	public QuesReplyVO get(Long rno);

	public int modify(QuesReplyVO vo);

	public int remove(Long rno);

	public List<QuesReplyVO> getList(QuesCriteria cri, Long quesNo);

	// (434)
	public QuesReplyPageDTO getListPage(QuesCriteria cri, Long quesNo);
}
