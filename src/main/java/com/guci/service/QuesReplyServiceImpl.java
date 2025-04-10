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
public class QuesReplyServiceImpl implements QuesReplyService {

	@Autowired
	private QuesReplyMapper mapper;

	@Autowired
	private QuesMapper quesMapper;

	/*
	  Q&A記事に対するコメントを登録し、該当記事のコメント数を1増加させる
	 */
	@Transactional
	@Override
	public int register(QuesReplyVO vo) {
		log.info("register..." + vo);

		quesMapper.updateReplyCnt(vo.getQuesNo(), 1);

		return mapper.insert(vo);
	}

	/*
	  コメント番号を基に、コメント情報を取得する
	 */
	@Override
	public QuesReplyVO get(Long rno) {
		log.info("get..." + rno);
		return mapper.read(rno);
	}

	/*
	  コメントを修正する
	 */
	@Override
	public int modify(QuesReplyVO vo) {
		log.info("modify..." + vo);
		return mapper.update(vo);
	}

	/*
	  コメントを削除し、該当記事のコメント数を1減少させる
	 */
	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("remove..." + rno);

		QuesReplyVO vo = mapper.read(rno);

		quesMapper.updateReplyCnt(vo.getQuesNo(), -1);
		return mapper.delete(rno);
	}

	/*
	  指定されたQ&A記事に関連するコメント一覧を、ページング付きで取得する
	 */
	@Override
	public List<QuesReplyVO> getList(QuesCriteria cri, Long quesNo) {
		log.info("get Reply List of a Board" + quesNo);

		return mapper.getListWithPaging(cri, quesNo);
	}

	/*
	  コメントの総数と、ページングされたコメントリストをDTO形式で返却する
	 */
	@Override
	public QuesReplyPageDTO getListPage(QuesCriteria cri, Long quesNo) {
		return new QuesReplyPageDTO(
				mapper.getCountByQuesNo(quesNo),
				mapper.getListWithPaging(cri, quesNo));
	}


}
