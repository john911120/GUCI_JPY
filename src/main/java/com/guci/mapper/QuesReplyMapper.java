package com.guci.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesReplyVO;

//(378) ReplyMapper 인터페이스 생성
public interface QuesReplyMapper {
	//(381) create
	public int insert(QuesReplyVO vo);

	// (384) read
	public QuesReplyVO read(Long quesNo);

	// (385) delete
	public int delete(Long quesNo);

	// (386) update
	public int update(QuesReplyVO reply);

	// (387) 댓글 목록
	public List<QuesReplyVO> getListWithPaging(
			@Param("cri") QuesCriteria cri,
			@Param("quesNo") Long quesNo);

	// (432) 댓글 숫자 파악하기
	public int getCountByQuesNo(Long quesNo);
}
