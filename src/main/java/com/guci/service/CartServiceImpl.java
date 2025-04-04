package com.guci.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guci.domain.CartVO;
import com.guci.domain.TestVO;
import com.guci.mapper.CartMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Service
@Log4j
@AllArgsConstructor
public class CartServiceImpl  implements CartService{
	
	private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
	
	//@Setter(onMethod_=@Autowired)
	@Autowired
	private CartMapper mapper;
	
	
	// カート情報をDBに追加します。
	@Override
	public void addCart(CartVO cart) {
		mapper.addCart(cart);

	}


	//  ユーザーごとにカートに追加した商品をDBから取得します。
	@Override
	public List<CartVO> cartList(String userId) {

		return mapper.cartList(userId);
	}


	//  ユーザーがカートに追加した商品をDBから削除します。
	@Override
	public void deleteCart(TestVO vo) {
		mapper.deleteCart(vo);

	}


	// ユーザーのカートにある商品の合計金額を取得します。
	@Override
	public Long sumCart(String userId) {
		return mapper.sumCart(userId);
	}


}
