package com.guci.service;

import com.guci.domain.OrderInfoVO;
import com.guci.domain.OrderVO;

public interface OrderService {
	public void insertInfo(OrderInfoVO ivo);

	public void orderInsert(OrderVO vo);

	public int deleteInfo(String userId);

	public int cartAllDelete(String userId);

	//유저테이블에 총 구매액 업데이트
	public int updateUser(String userId);
}
