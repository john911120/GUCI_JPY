package com.guci.mapper;

import java.util.List;

import com.guci.domain.ReviewAttachVO;

public interface ReviewAttachMapper {
	public void insert(ReviewAttachVO vo);

	public void delete(String uuid);

	public List<ReviewAttachVO> findByRevNo(Long revNo);

	public void deleteAll(Long revNo);

	public List<ReviewAttachVO> getOldFiles();
}
