package com.guci.service;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.guci.domain.UserVO;
import com.guci.mapper.UserMapper;

/*
  ユーザー関連サービスの実装クラス
  DBへのアクセスはUserMapperを通じて行う。
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	/*
	  ユーザー登録（DBに保存）
	 */
	@Override
	public void userJoin(UserVO user) throws Exception {
		mapper.userJoin(user);
	}

	/*
	  IDの重複チェック
	 */
	@Override
	public int idCheck(String userId) throws Exception {
		return mapper.idCheck(userId);
	}

	/*
	  ログイン処理（IDとパスワード照合）
	 */
	@Override
	public UserVO userLogin(UserVO user) throws Exception {
		return mapper.userLogin(user);
	}

	/*
	  ID検索（名前・メールアドレスに基づく）
	 */
	@Override
	public UserVO searchId(UserVO vo) throws Exception {
		return mapper.searchId(vo);
	}

	/*
	  ユーザー情報取得（ID基準）
	 */
	@Override
	public UserVO user(String userId) throws Exception {
		return mapper.user(userId);
	}

	/*
	  パスワード取得（暗号化済）
	 */
	@Override
	public String userPw(String userId) throws Exception {
		return mapper.userPw(userId);
	}

	/*
	  ユーザー情報更新
	 */
	@Override
	public int updateInfo(UserVO user) throws Exception {
		return mapper.updateInfo(user);
	}

	/*
	  パスワード検索処理（条件一致時に仮パスワードを送信）
	 */
	@Override
	public void searchPw(HttpServletResponse response, UserVO vo) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// IDが存在しない場合
		if (mapper.user(vo.getUserId()) == null) {
			out.print("IDが存在しません。");
			out.close();
		}
		// メールアドレスが一致しない場合
		else if (!vo.getUserEmail().equals(mapper.findEmail(vo.getUserId()))) {
			out.print("メールアドレス一致しません。");
			out.close();
		}
		// 条件一致 → 仮パスワード生成＆送信
		else {
			// 仮パスワード生成（12文字のランダム）
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			vo.setUserPw(pw);

			// 仮パスワード送信（メール）
			sendEmail(vo, "findPw");

			// 暗号化してDB更新
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String password = encoder.encode(vo.getUserPw());
			vo.setUserPw(password);
			mapper.updatePw(vo);

			out.print("メールアドレスで臨時パスワードを送りました。ご確認お願い致します。");
			out.close();
		}
	}

	/*
	  メール送信処理（仮パスワードなど）
	 */
	@Override
	public void sendEmail(UserVO vo, String div) throws Exception {
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = "tlsdudfuf124@naver.com";
		String hostSMTPpwd = "!eui050800";
		String fromEmail = "tlsdudfuf124@naver.com";
		String fromName = "GUCIショッピングモール";

		String subject = "";
		String msg = "";

		if (div.equals("findPw")) {
			subject = "GUCIショッピングモール 仮パスワードのご案内";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getUserId() + "様の仮パスワードです。パスワードを変更してご利用ください。</h3>";
			msg += "<p>仮パスワード : " + vo.getUserPw() + "</p></div>";
		}

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
			System.out.println("メール送信失敗 : " + e);
		}
	}

	/*
	  ユーザー削除（退会）
	 */
	@Override
	public int delete(String userId) {
		return mapper.delete(userId);
	}
}
