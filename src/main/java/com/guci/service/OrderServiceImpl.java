package com.guci.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guci.domain.OrderInfoVO;
import com.guci.domain.OrderVO;
import com.guci.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {
	/*
	   商品注文に関するデータベース操作を担当する
	   DBへのアクセスはOrderMapperを通じて行う。
	 */ 

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private OrderMapper mapper
	;
	
	// 注文情報を追加します。
	@Override
	public void insertInfo(OrderInfoVO ivo) {
		mapper.insertInfo(ivo);
	}

	// 注文商品を追加します。
	@Override
	public void orderInsert(OrderVO vo) {
		mapper.orderInsert(vo);
	}

	// 注文情報を削除します。
	@Override
	public int deleteInfo(String userId) {
		return mapper.deleteInfo(userId);
	}

	// カートに入った商品を全部削除します。
	@Override
	public int cartAllDelete(String userId) {
		return mapper.cartAllDelete(userId);
	}

	//ユーザーデーブルの総金額を更新します。
	@Override
	public int updateUser(String userId) {
		return mapper.updateUser(userId);
	}

}
