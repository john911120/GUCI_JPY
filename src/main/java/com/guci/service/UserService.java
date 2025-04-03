package com.guci.service;


import javax.servlet.http.HttpServletResponse;

import com.guci.domain.UserVO;

public interface UserService {

	public void userJoin(UserVO user) throws Exception;

	public int idCheck(String userId) throws Exception;

	public UserVO userLogin(UserVO user) throws Exception;

	public UserVO searchId(UserVO vo) throws Exception;



	public UserVO user(String userId) throws Exception;

	public String userPw(String userId) throws Exception;

	public int updateInfo(UserVO user) throws Exception;

//	public int deleteInfo(String userId) throws Exception;
	public int delete(String userId);

	//비밀번호 찾기
	public void searchPw(HttpServletResponse response,UserVO vo) throws Exception;

	//임시 비밀번호 이메일발송
	public void sendEmail(UserVO vo,String div) throws Exception;
}
