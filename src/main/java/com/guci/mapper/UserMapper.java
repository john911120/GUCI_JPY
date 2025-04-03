package com.guci.mapper;


import org.springframework.transaction.annotation.Transactional;

import com.guci.domain.UserVO;

public interface UserMapper {

	//회원가입
		public void userJoin(UserVO user);
	// 아이디 중복 검사
		public int idCheck(String userId);
    // 비밀번호 체크
		public int pwCheck(String userPw);
	// 로그인
	    public UserVO userLogin(UserVO user);
	//아이디 찾기
	    public UserVO searchId(UserVO vo) throws Exception;


	//이메일 찾기
	public String findEmail(String userid) throws Exception;

	//비밀번호 찾기(임시비밀번호 업데이트)
	@Transactional
	public int updatePw(UserVO vo) throws Exception;

	//원래 비밀번호 가져오기
	    public String userPw(String userId);

	//회원정보 가져오기
	    public UserVO user(String userId);

	//회원정보 변경하기
	    public int updateInfo(UserVO user);

	//회원탈퇴
//	    public int deleteInfo(String userId);
	    public int delete(String userId);
}
