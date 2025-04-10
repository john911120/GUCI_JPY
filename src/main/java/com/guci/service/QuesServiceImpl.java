package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guci.domain.QuesAttachVO;
import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesVO;
import com.guci.mapper.QuesAttachMapper;
import com.guci.mapper.QuesMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
//@AllArgsConstructor
public class QuesServiceImpl implements QuesService {

	@Autowired
	private QuesMapper mapper;

	@Autowired
	private QuesAttachMapper attachMapper;

	/*
	  Q&A記事を登録する
	 */
	@Transactional
	@Override
	public void register(QuesVO ques) {
		log.info("register....." + ques);
		mapper.insertSelectKey(ques);
		if(ques.getAttachList() == null || ques.getAttachList().size() <= 0) {
			return;
		}

		ques.getAttachList().forEach(attach -> {
			attach.setQuesNo(ques.getQuesNo());
			attachMapper.insert(attach);
		});
	}

	// 特定のQ&A記事に対するコメント一覧
	@Override
	public List<QuesVO> getList(QuesCriteria cri) {
		log.info("Get List with criteria : " + cri);
		return mapper.getListWithPaging(cri);
	}

	// Q&A記事の詳細を取得します
	@Override
	public QuesVO get(Long quesNo) {
		log.info("get..." + quesNo);
		return mapper.read(quesNo);
	}
	
	// Q&A記事を更新します
	@Transactional
	@Override
	public boolean modify(QuesVO ques) {
		log.info("modify..." + ques);

		attachMapper.deleteAll(ques.getQuesNo());

		boolean modifyResult = mapper.update(ques) == 1;
		if(modifyResult && ques.getAttachList() != null && ques.getAttachList().size() > 0) {
			ques.getAttachList().forEach(attach -> {
				attach.setQuesNo(ques.getQuesNo());
				attachMapper.insert(attach);
			});
		}
		return modifyResult;
	}
	
	// Q&A記事を削除します
	@Transactional
	@Override
	public boolean remove(Long quesNo) {
		log.info("remove..." + quesNo);

		attachMapper.deleteAll(quesNo);

		return mapper.delete(quesNo) == 1;
	}

	// Q&Aの総記事数を取得（ページングのため）
	@Override
	public int getTotal(QuesCriteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	// Q&A記事と添付ファイルを取得します
	@Override
	public List<QuesAttachVO> getAttachList(Long quesNo) {
		log.info("get Attach list by quesNo : " + quesNo);
		return attachMapper.findByquesNo(quesNo);
	}

}
