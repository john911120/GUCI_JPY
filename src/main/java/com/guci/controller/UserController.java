package com.guci.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guci.domain.UserVO;
import com.guci.service.UserService;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/user")
@Log4j
public class UserController {

	@Autowired
	private UserService userservice;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private BCryptPasswordEncoder pwEncoder;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void loginGET() {

		log.info("会員加入ページへ移動");
	}

	// 회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(UserVO user) throws Exception {

		String rawPw = ""; // 인코딩 전 비밀번호
		String encodePw = ""; // 인코딩 후 비밀번호

		rawPw = user.getUserPw(); // 비밀번호 데이터 얻음
		encodePw = pwEncoder.encode(rawPw); // 비밀번호 인코딩
		user.setUserPw(encodePw); // 인코딩된 비밀번호 user객체에 다시 저장

		/* 회원가입 쿼리 실행 */
		userservice.userJoin(user);

		return "redirect:/";

	}

	// 로그인 페이지 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void accountGET() {

		log.info("ログインページへ移動");

	}

	// 아이디 중복 검사
	@RequestMapping(value = "/userIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String userIdChkPOST(String userId) throws Exception {

		log.info("userIdChk() チェック");
		int result = userservice.idCheck(userId);
		log.info("結果 = " + result);
		if (result != 0) {
			return "fail"; // 중복 아이디가 존재
		} else {
			return "success"; // 중복 아이디 x
		}
	} // userIdChkPOST() 종료

	/* 이메일 인증 */
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {

		/* 뷰(View)로부터 넘어온 데이터 확인 */
		log.info("メールアドレス データ転送確認");
		log.info("認証番号 : " + email);

		/* 인증번호(난수) 생성 */
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		log.info("認証番号 " + checkNum);

		/* 이메일 보내기 */
		String setFrom = "zndn121@naver.com";
		String toMail = email;
		String title = "会員加入認証メールです。";
		String content = "認証番号は " + checkNum + "です。" + "<br>"
				+ "認証番号を入力してください。";
		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String num = Integer.toString(checkNum);

		return num;
	}


	/* 로그인 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPOST(HttpServletRequest request, UserVO user, RedirectAttributes rttr) throws Exception {
		log.info(user);
		HttpSession session = request.getSession();
		String rawPw = "";
		String encodePw = "";
		UserVO lvo = userservice.userLogin(user); // 제출한 아이디와 일치하는 아이디인지


		if (lvo != null) { // 일치하는 아이디 존재시

			rawPw = user.getUserPw(); // 사용자가 제출한 비밀번호
			encodePw = lvo.getUserPw(); // 데이터베이스에 저장한 인코딩된 비밀번호

			if (pwEncoder.matches(rawPw, encodePw)) { // 비밀번호 일치여부 판단

				lvo.setUserPw(""); // 인코딩된 비밀번호 정보 지움
				session.setAttribute("user", lvo); // session에 사용자의 정보 저장
				return "redirect:/"; // 메인페이지 이동

			} else {

				rttr.addFlashAttribute("result", 0);
				return "redirect:/user/login"; // 로그인 페이지로 이동

			}

		} else { // 일치하는 아이디가 존재하지 않을 시 (로그인 실패)

			rttr.addFlashAttribute("result", 0);
			return "redirect:/user/login"; // 로그인 페이지로 이동

		}

	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}

	// 아이디 찾기 페이지 이동
	@RequestMapping(value="/searchId" )
	public String searchId() {
		return "/user/searchId";
	}

	// 비밀번호 찾기 페이지 이동
	@RequestMapping("/searchPw")
	public String searchPw() {
		return "/user/searchPw";
	}

	// 아이디 찾기
	@RequestMapping(value = "/searchId", method = RequestMethod.POST)
	public String searchId(UserVO vo, Model model) throws Exception {
		UserVO user = userservice.searchId(vo);

		if(user == null) {
			model.addAttribute("check", 1);
		}else {
			model.addAttribute("check", 0);
			model.addAttribute("userId", user.getUserId());
		}
		return "user/searchId";
	}

	// 비밀번호 변경
		@RequestMapping(value = "/searchPw", method = RequestMethod.POST)
		public void searchPw(@ModelAttribute UserVO vo, HttpServletResponse response) throws Exception {
			userservice.searchPw(response, vo);


		}



	@GetMapping("/mypage")
	public void mypage() {}

//	@RequestMapping(value="/mypageCheckPw", method=RequestMethod.GET)
//	public void mypageCheckPw(HttpSession session, Model model) throws Exception {
//		Object user = session.getAttribute("user");
//		String userId = ((UserVO) user).getUserId();
//		String userPw = userservice.userPw(userId);
//		model.addAttribute("userPw", userPw);
//	}

	@GetMapping("/mypageCheckPw")
	public void mypageCheckPwGet() {

	}

	@PostMapping("/mypageCheckPw")
	public String update_info(HttpServletRequest request, UserVO user, RedirectAttributes rttr) throws Exception {

		HttpSession session = request.getSession();
		String rawPw = "";
		String encodePw = "";
		UserVO lvo = userservice.userLogin(user); // 제출한 아이디와 일치하는 아이디인지


		if (lvo != null) { // 일치하는 아이디 존재시

			rawPw = user.getUserPw(); // 사용자가 제출한 비밀번호
			encodePw = lvo.getUserPw(); // 데이터베이스에 저장한 인코딩된 비밀번호

			if (pwEncoder.matches(rawPw, encodePw)) { // 비밀번호 일치여부 판단

				lvo.setUserPw(""); // 인코딩된 비밀번호 정보 지움
				session.setAttribute("user", lvo); // session에 사용자의 정보 저장
				return "redirect:/user/update_info"; // 메인페이지 이동

			} else {

				rttr.addFlashAttribute("result", 0);
				return "redirect:/user/mypageCheckPw"; // 로그인 페이지로 이동

			}

		} else { // 일치하는 아이디가 존재하지 않을 시 (로그인 실패)

			rttr.addFlashAttribute("result", 0);
			return "redirect:/user/mypageCheckPw"; // 로그인 페이지로 이동

		}

	}

	@GetMapping("/update_info")
	public void update_info(HttpSession session, Model model) throws Exception {
		Object user = session.getAttribute("user");
		String userId = ((UserVO) user).getUserId();
		UserVO vo = userservice.user(userId);
		model.addAttribute("user", vo);
	}

//	@GetMapping("/update_info")
//	public void update_info() {}


	@PostMapping("/update_info")
	public String update(HttpSession session, UserVO user, RedirectAttributes rttr) throws Exception {
		String rawPw = ""; // 인코딩 전 비밀번호
		String encodePw = ""; // 인코딩 후 비밀번호

		rawPw = user.getUserPw(); // 비밀번호 데이터 얻음
		encodePw = pwEncoder.encode(rawPw); // 비밀번호 인코딩

		Object user1 = session.getAttribute("user");
		String userId = ((UserVO) user1).getUserId();

		UserVO vo = userservice.user(userId);
		vo.setUserPw(encodePw); // 인코딩된 비밀번호 user객체에 다시 저장

		userservice.updateInfo(vo);

		return "redirect:/";

	}

	@GetMapping("/delete_info")
	public void delete_info() {}

	@PostMapping("/delete_info")
	public String delete(HttpSession session) throws Exception {
		log.info("会員脱退");
		Object user = session.getAttribute("user");
		String userId = ((UserVO) user).getUserId();
		log.info(userId);

		userservice.delete(userId);
		session.invalidate();
		return "/";
	}

}
