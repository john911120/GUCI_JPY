package com.guci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guci.domain.LatelyOrderVO;
import com.guci.mapper.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService{

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private AdminMapper mapper;
	
	/*
	 * 未回答の質問件数をDBから取得します。
	 */
	@Override
	public int questionNo() {
		return mapper.questionNo();
	}

    /*
     * 最近の注文データをDBから取得し、VOとして返却します。
     */
	@Override
	public List<LatelyOrderVO> latelyOrderList() {
		return mapper.latelyOrderList();
	}

    /*
     * 累積売上金額を取得します。
     */
	@Override
	public int totalIncome() {
		return mapper.totalIncome();
	}

    /*
     * 当日売上金額（文字列形式）を取得します。
     */
	@Override
	public String todayIncome() {
		return mapper.todayIncome();
	}
	
    /*
     * 当日販売数量（文字列形式）を取得します。
     */
	@Override
	public String todaySalesQuantity() {
		return mapper.todaySalesQuantity();
	}

}
