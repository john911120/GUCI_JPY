package com.guci.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guci.domain.OrderInfoVO;
import com.guci.domain.OrderVO;
import com.guci.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private OrderMapper mapper
	;

	@Override
	public void insertInfo(OrderInfoVO ivo) {
		mapper.insertInfo(ivo);
	}

	@Override
	public void orderInsert(OrderVO vo) {
		mapper.orderInsert(vo);
	}

	@Override
	public int deleteInfo(String userId) {
		return mapper.deleteInfo(userId);
	}

	@Override
	public int cartAllDelete(String userId) {
		return mapper.cartAllDelete(userId);
	}

	@Override
	public int updateUser(String userId) {
		return mapper.updateUser(userId);
	}

}
