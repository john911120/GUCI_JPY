package com.guci.mapper;

import java.util.List;

import com.guci.domain.GoodsAttachVO;

public interface GoodsAttachMapper {
	public void insert(GoodsAttachVO vo);
	public void delete(Long gdsNo);
	public List<GoodsAttachVO> findByGdsNo(Long gdsNo);
	public void deleteAll(Long gdsNo);
	public List<GoodsAttachVO> getOldFiles();

}
