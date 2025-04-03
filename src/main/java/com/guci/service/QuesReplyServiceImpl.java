package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesReplyPageDTO;
import com.guci.domain.QuesReplyVO;
import com.guci.mapper.QuesMapper;
import com.guci.mapper.QuesReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
// (390~91)
public class QuesReplyServiceImpl implements QuesReplyService {
	// (484) 트랜잭션 처리
	//@Setter(onMethod_= @Autowired)
	@Autowired
	private QuesReplyMapper mapper;

	//@Setter(onMethod_= @Autowired)
	@Autowired
	private QuesMapper quesMapper;


	@Transactional
	@Override
	public int register(QuesReplyVO vo) {
		log.info("register..." + vo);

		quesMapper.updateReplyCnt(vo.getQuesNo(), 1);

		return mapper.insert(vo);
	}

	@Override
	public QuesReplyVO get(Long rno) {
		log.info("get..." + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(QuesReplyVO vo) {
		log.info("modify..." + vo);
		return mapper.update(vo);
	}
//	@Override
//	public int remove(Long replyNo) {
//		log.info("remove..." + replyNo);
//
//		return mapper.delete(replyNo);
//	}

	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("remove..." + rno);

		QuesReplyVO vo = mapper.read(rno);

		quesMapper.updateReplyCnt(vo.getQuesNo(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<QuesReplyVO> getList(QuesCriteria cri, Long quesNo) {
		log.info("get Reply List of a Board" + quesNo);

		return mapper.getListWithPaging(cri, quesNo);
	}

	// (434~5)
	@Override
	public QuesReplyPageDTO getListPage(QuesCriteria cri, Long quesNo) {

		return new QuesReplyPageDTO(
				mapper.getCountByQuesNo(quesNo),
				mapper.getListWithPaging(cri, quesNo));
	}


}
