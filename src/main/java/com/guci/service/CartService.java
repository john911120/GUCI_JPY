package com.guci.service;


import java.util.List;

import com.guci.domain.CartVO;
import com.guci.domain.TestVO;


public interface CartService {


	public void addCart(CartVO cart);

	public List<CartVO> cartList(String userId);

	public void deleteCart(TestVO vo);

	public Long sumCart(String userId);
}
