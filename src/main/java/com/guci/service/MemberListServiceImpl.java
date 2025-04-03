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

	@Override
	public List<MemberListVO> getList() {
		return mapper.getList();
	}

}
