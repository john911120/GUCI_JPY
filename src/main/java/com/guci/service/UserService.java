package com.guci.service;


import javax.servlet.http.HttpServletResponse;

import com.guci.domain.UserVO;

/*
  ユーザー関連のサービスインターフェース
  各メソッドはコントローラーから呼び出され、ビジネスロジックを担当する。
 */
public interface UserService {

    /*
      ユーザー新規登録
     */
    public void userJoin(UserVO user) throws Exception;

    /*
      ユーザーIDの重複チェック
      @return 既存なら 1, 存在しなければ 0
     */
    public int idCheck(String userId) throws Exception;

    /*
      ログイン処理
      @return 一致するユーザー情報（存在しない場合は null）
     */
    public UserVO userLogin(UserVO user) throws Exception;

    /*
      ID検索
     */
    public UserVO searchId(UserVO vo) throws Exception;

    /*
      ユーザー情報の取得（ID基準）
     */
    public UserVO user(String userId) throws Exception;

    /*
      ユーザーのパスワード取得（暗号化済）
     */
    public String userPw(String userId) throws Exception;

    /*
      ユーザー情報更新
     */
    public int updateInfo(UserVO user) throws Exception;

    /*
      会員退会
     */
    public int delete(String userId);

    /*
      パスワード検索処理
      条件に合致すれば、メールにて仮パスワードを送信
     */
    public void searchPw(HttpServletResponse response, UserVO vo) throws Exception;

    /*
      仮パスワードメール送信処理
      @param div メールの種別（"findPw" など）
     */
    public void sendEmail(UserVO vo, String div) throws Exception;
}