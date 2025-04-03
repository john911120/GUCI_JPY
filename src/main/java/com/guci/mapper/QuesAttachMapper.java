package com.guci.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guci.domain.QuesAttachVO;

public interface QuesAttachMapper {
	// (552) 첨부파일 처리를 위한 mapper
	public void insert(QuesAttachVO vo);

	public void delete(String uuid);

	public List<QuesAttachVO> findByquesNo(Long quesNo);

	// (578) 첨부파일 삭제 처리
	public void deleteAll(@Param("quesNo") Long quesNo);

	// (600) 데이터베이스에 저장된 모든 파일의 목록을 사용하기 위한 코드 추가
	public List<QuesAttachVO> getOldFiles();
}
