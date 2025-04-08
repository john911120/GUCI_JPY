package com.guci.mapper;

import java.util.List;

import com.guci.domain.GoodsAttachVO;

public interface GoodsAttachMapper {
	
	// 商品の添付ファイルを追加する。
	public void insert(GoodsAttachVO vo);
	
	// 商品の添付ファイルを削除する。
	public void delete(Long gdsNo);
	
	// 商品の番号を使って検索する。
	public List<GoodsAttachVO> findByGdsNo(Long gdsNo);
	
	// 商品の添付ファイルを全部削除する。
	public void deleteAll(Long gdsNo);
	
	// 古い商品の添付ファイル情報を取得する。
	public List<GoodsAttachVO> getOldFiles();
	

}
