package com.guci.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guci.domain.CartVO;
import com.guci.domain.TestVO;
import com.guci.domain.UserVO;
import com.guci.service.CartService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/*
	注文に使うカートを管理するサービス
	@Autowired
	private CartService service;
*/

@Controller
@Log4j
@AllArgsConstructor
public class CartController {

//	@Setter(onMethod_= @Autowired)
	@Autowired
	private CartService service;


	/*
  	order : 
	注文用に追加した商品を表示するカートページです。
	*/	
	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public String order(HttpSession session) throws Exception {

		/* MemberVO member = (MemberVO)session.getAttribute("member"); */

	 return "cart";
	}
	
	/*
	 addCart : 
	ユーザー情報を確認して、商品をカートに追加します。
	*/
	@RequestMapping(value="/addCart", method= {RequestMethod.GET,RequestMethod.POST})
	public String addCart(CartVO cart, HttpSession session) throws Exception {
		Object user = session.getAttribute("user");
		String userId = ((UserVO) user).getUserId();
		cart.setUserId(userId);
		service.addCart(cart);

	 return "cart";


	}

	/*
	 getCartList : 
	  ユーザーごとにカートに追加した商品を表示します。
	*/
	@RequestMapping(value="/cart",method=RequestMethod.GET)
	public ModelAndView getCartList(HttpSession session, ModelAndView mav) {

		Object user = session.getAttribute("user");
		String userId = ((UserVO) user).getUserId();
		Map<String, Object> map = new HashMap<>();
		List<CartVO>cartList =service.cartList(userId);
		Long allSum = service.sumCart(userId); //カートの総金額を呼び出す
		map.put("list", cartList);
		map.put("allSum", allSum);
		mav.setViewName("/cart");
		mav.addObject("map", map);

		return mav;

	}
	/*
	 	deleteCart : 
	 	ユーザーごとにカートに追加した商品を削除します。 	
	 */
	
	@ResponseBody
	@RequestMapping(value = "/deleteCart", method = RequestMethod.POST)
	public int deleteCart(HttpSession session,
	     @RequestParam(value = "chbox[]") List<String> chArr, TestVO cart) throws Exception {
	
		Object user = session.getAttribute("user");
		String userId = ((UserVO) user).getUserId();
		 int result = 0;
		 int cartNo = 0;
		 for(String i : chArr) {
			  log.info("chArr : "+ chArr);
			   cartNo = Integer.parseInt(i);
			   cart.setCartNo(cartNo);
			   cart.setUserId(userId);
			   service.deleteCart(cart);
	 }
	 result=1;
	
	
	 return result;
	}


}

