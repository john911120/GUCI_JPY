package com.guci.mapper;

import java.util.List;

import com.guci.domain.CartVO;
import com.guci.domain.TestVO;

public interface CartMapper {

	//장바구니 추가
	public void addCart(CartVO cart);
	//장바구니 목록
	public List<CartVO> cartList (String userId);
	//장바구니 삭제
	public void deleteCart(TestVO vo);
	//총 금액
	public Long sumCart(String userId);
}
