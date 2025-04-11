package com.guci.mapper;

import java.util.List;

import com.guci.domain.MemberListVO;

public interface MemberListMapper {
	
	/*
	getList：
	会員の注文情報を一覧で取得します。

	@return List<MemberListVO> 会員情報のリスト。以下のフィールドを含みます：
	    - ユーザーID（String型）
	    - ユーザー名（String型）
	    - 注文番号（int型）
	    - 注文価格（int型）
	    - 登録日（Date型）
	    - 在庫数（Long型）
	    - 合計数量（Long型）
	 */
	
	public List<MemberListVO> getList();
}
