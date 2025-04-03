package com.guci.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guci.domain.QuesCriteria;
import com.guci.domain.QuesVO;

public interface QuesMapper {

	// Method1 : xml을 사용하지 않는 방법
//	@Select("select * from tbl_board where bno > 0")



	public List<QuesVO> getlist();
	// 294
	public List<QuesVO> getListWithPaging(QuesCriteria cri);

	// Insert
	public void insert(QuesVO board);

	// 294
	public Integer insertSelectKey(QuesVO board);

	// Read
	public QuesVO read(Long quesNo);

	// Delete
	public int delete(Long quesNo);

	// Update
	public int update(QuesVO board);

	// p322 마이바티스에서 전체 데이터 개수 처리를 확인하는 방법
	public int getTotalCount(QuesCriteria cri);

	// (482)
	public void updateReplyCnt(@Param("quesNo") Long quesNo, @Param("amount") int amount);
}
