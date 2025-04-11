package com.guci.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesReplyVO;

//(378) ReplyMapper 인터페이스 생성
public interface QuesReplyMapper {

	// Q&A記事に対するコメントを登録し、該当記事のコメント数を1増加させる
	public int insert(QuesReplyVO vo);

	// コメント番号を基に、コメント情報を取得する

	public QuesReplyVO read(Long quesNo);

	// コメントを削除し、該当記事のコメント数を1減少させる

	public int delete(Long quesNo);

	// コメントを修正する

	public int update(QuesReplyVO reply);

	// 指定されたQ&A記事に関連するコメント一覧を、ページング付きで取得する

	public List<QuesReplyVO> getListWithPaging(
			@Param("cri") QuesCriteria cri,
			@Param("quesNo") Long quesNo);

	// コメントの総数と、ページングされたコメントリストをDTO形式で返却する
	public int getCountByQuesNo(Long quesNo);
}
