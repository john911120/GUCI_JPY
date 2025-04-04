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

	/*
	  会員登録ページへ遷移
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void loginGET() {
		log.info("会員加入ページへ移動");
	}

	/*
	  会員登録処理（パスワード暗号化を含む）
	 */
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(UserVO user) throws Exception {
		String rawPw = user.getUserPw(); // 入力されたパスワード
		String encodePw = pwEncoder.encode(rawPw); // パスワードを暗号化
		user.setUserPw(encodePw); // ユーザーオブジェクトにセット
		userservice.userJoin(user); // DBに登録
		return "redirect:/";
	}

	/*
	  ログインページへ遷移
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void accountGET() {
		log.info("ログインページへ移動");
	}

	/*
	  ユーザーID重複チェック（Ajax用）
	  @return "fail" または "success"
	 */
	@RequestMapping(value = "/userIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String userIdChkPOST(String userId) throws Exception {
		log.info("userIdChk() チェック");
		int result = userservice.idCheck(userId);
		log.info("結果 = " + result);
		return (result != 0) ? "fail" : "success";
	}

	/*
	 メール認証コードの送信
	 @return 認証番号（6桁のランダム数字）
	 */
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {
		log.info("メールアドレス データ転送確認");
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		String setFrom = "zndn121@naver.com";
		String content = "認証番号は " + checkNum + " です。<br>認証番号を入力してください。";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(email);
			helper.setSubject("会員加入認証メールです。");
			helper.setText(content, true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.toString(checkNum);
	}

	/*
	  ログイン処理（パスワード照合 + セッション保存）
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPOST(HttpServletRequest request, UserVO user, RedirectAttributes rttr) throws Exception {
		HttpSession session = request.getSession();
		UserVO lvo = userservice.userLogin(user);
		if (lvo != null && pwEncoder.matches(user.getUserPw(), lvo.getUserPw())) {
			lvo.setUserPw("");
			session.setAttribute("user", lvo);
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("result", 0);
			return "redirect:/user/login";
		}
	}

	/*
	  ログアウト処理（セッション削除）
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	/*
	  ID検索ページへ遷移
	 */
	@RequestMapping(value="/searchId")
	public String searchId() {
		return "/user/searchId";
	}

	/*
	  パスワード検索ページへ遷移
	 */
	@RequestMapping("/searchPw")
	public String searchPw() {
		return "/user/searchPw";
	}

	/*
	  ID検索処理
	 */
	@RequestMapping(value = "/searchId", method = RequestMethod.POST)
	public String searchId(UserVO vo, Model model) throws Exception {
		UserVO user = userservice.searchId(vo);
		if (user == null) {
			model.addAttribute("check", 1);
		} else {
			model.addAttribute("check", 0);
			model.addAttribute("userId", user.getUserId());
		}
		return "user/searchId";
	}

	/*
	  パスワード変更処理
	 */
	@RequestMapping(value = "/searchPw", method = RequestMethod.POST)
	public void searchPw(@ModelAttribute UserVO vo, HttpServletResponse response) throws Exception {
		userservice.searchPw(response, vo);
	}

	/*
	  マイページへ遷移
	 */
	@GetMapping("/mypage")
	public void mypage() {}

	/*
	  マイページ：パスワード確認ページ（GET）
	 */
	@GetMapping("/mypageCheckPw")
	public void mypageCheckPwGet() {}

	/*
	  マイページ：パスワード確認処理（POST）
	 */
	@PostMapping("/mypageCheckPw")
	public String update_info(HttpServletRequest request, UserVO user, RedirectAttributes rttr) throws Exception {
		HttpSession session = request.getSession();
		UserVO lvo = userservice.userLogin(user);
		if (lvo != null && pwEncoder.matches(user.getUserPw(), lvo.getUserPw())) {
			lvo.setUserPw("");
			session.setAttribute("user", lvo);
			return "redirect:/user/update_info";
		} else {
			rttr.addFlashAttribute("result", 0);
			return "redirect:/user/mypageCheckPw";
		}
	}

	/*
	  会員情報編集ページへ遷移（GET）
	 */
	@GetMapping("/update_info")
	public void update_info(HttpSession session, Model model) throws Exception {
		Object user = session.getAttribute("user");
		String userId = ((UserVO) user).getUserId();
		UserVO vo = userservice.user(userId);
		model.addAttribute("user", vo);
	}

	/*
	  会員情報更新処理（パスワード再設定含む）
	 */
	@PostMapping("/update_info")
	public String update(HttpSession session, UserVO user, RedirectAttributes rttr) throws Exception {
		String encodePw = pwEncoder.encode(user.getUserPw());
		Object user1 = session.getAttribute("user");
		String userId = ((UserVO) user1).getUserId();
		UserVO vo = userservice.user(userId);
		vo.setUserPw(encodePw);
		userservice.updateInfo(vo);
		return "redirect:/";
	}

	/*
	  会員退会ページへ遷移
	 */
	@GetMapping("/delete_info")
	public void delete_info() {}

	/*
	  会員退会処理
	 */
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
