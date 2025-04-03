package com.guci.service;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.guci.domain.UserVO;
import com.guci.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
/* 240610 
  @Setter(onMethod_ = @Autowired) : 
  @Autowired를 사용하여 스프링 의존성을 주입할 수 있다.
	@Setter(onMethod_ = @Autowired)
	private UserMapper mapper;
*/
	@Autowired
	private UserMapper mapper;

	@Override
	public void userJoin(UserVO user) throws Exception {

		mapper.userJoin(user);

	}

	@Override
	public int idCheck(String userId) throws Exception {

		return mapper.idCheck(userId);
	}


	@Override
	public UserVO userLogin(UserVO user) throws Exception {

		return mapper.userLogin(user);
	}

	@Override
	public UserVO searchId(UserVO vo) throws Exception {

		return mapper.searchId(vo);
	}

	@Override
	public UserVO user(String userId) throws Exception {
		return mapper.user(userId);
	}


	@Override
	public String userPw(String userId) throws Exception {
		return mapper.userPw(userId);
	}

	@Override
	public int updateInfo(UserVO user) throws Exception {
		return mapper.updateInfo(user);
	}

//	@Override
//	public int deleteInfo(String userId) throws Exception {
//		return mapper.deleteInfo(userId);
//	}



	@Override
	public void searchPw(HttpServletResponse response, UserVO vo) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 아이디가 없으면
		if(mapper.user(vo.getUserId()) == null) {
			out.print("아이디가 없습니다.");
			out.close();
		}
		// 가입에 사용한 이메일이 아니면
		else if(!vo.getUserEmail().equals(mapper.findEmail(vo.getUserId()))) {
			out.print("잘못된 이메일 입니다.");
			out.close();
		}else {
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			vo.setUserPw(pw);
			System.out.println(vo.getUserPw());
			// 비밀번호 변경 메일 발송
			sendEmail(vo, "findPw");
			// 비밀번호 암호화 처리
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String password = encoder.encode(vo.getUserPw());
			vo.setUserPw(password);
			System.out.println(vo.getUserPw());
			// 비밀번호 변경
			mapper.updatePw(vo);

			out.print("이메일로 임시 비밀번호를 발송하였습니다.");
			out.close();
		}

	}

	@Override
	public void sendEmail(UserVO vo, String div) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = "tlsdudfuf124@naver.com";	//이메일 아이디@naver.com	== fromEmail
		String hostSMTPpwd = "!eui050800";	//이메일 비밀번호

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "tlsdudfuf124@naver.com"; //이메일 아이디
		String fromName = "GUCI쇼핑몰";
		String subject = "";
		String msg = "";

		if(div.equals("findPw")) {
			subject = "GUCI 쇼핑몰 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getUserId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += vo.getUserPw() + "</p></div>";
		}
		// 받는 사람 E-Mail 주소
		String mail = vo.getUserEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}

	}

	@Override
	public int delete(String userId) {
		return mapper.delete(userId);
	}

}
