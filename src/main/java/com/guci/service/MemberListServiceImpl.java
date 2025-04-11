package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guci.domain.MemberListVO;
import com.guci.mapper.MemberListMapper;

@Service
public class MemberListServiceImpl implements MemberListService {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private MemberListMapper mapper;

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
	
	@Override
	public List<MemberListVO> getList() {
		return mapper.getList();
	}

}
