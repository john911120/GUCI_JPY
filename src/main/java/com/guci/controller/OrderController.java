package com.guci.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guci.domain.OrderInfoVO;
import com.guci.domain.OrderVO;
import com.guci.domain.UserVO;
import com.guci.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class OrderController {

	//@Setter(onMethod_=@Autowired)
	@Autowired
	private OrderService service;

	@RequestMapping(value="/order", method=RequestMethod.POST)
	public String orderPost(HttpSession session, OrderInfoVO ivo, OrderVO vo) {
		Object user = session.getAttribute("user");
		String userId = ((UserVO) user).getUserId();
		ivo.setUserId(userId);
		vo.setUserId(userId);
		log.info("주문정보 : "+ivo);
		log.info("주문 : " +vo);
		service.insertInfo(ivo);
		service.orderInsert(vo);
		service.deleteInfo(userId);
		service.cartAllDelete(userId);
		service.updateUser(userId);
		return "/index";
	}

}
