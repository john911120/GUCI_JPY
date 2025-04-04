package com.guci.mapper;


import org.springframework.transaction.annotation.Transactional;

import com.guci.domain.UserVO;

/*
  ユーザー関連のデータベース操作を担当するマッパーインターフェース
  MyBatisによってSQLと連携される。
 */
public interface UserMapper {

    /*
      ユーザー新規登録（INSERT）
     */
    public void userJoin(UserVO user);

    /*
      ユーザーIDの重複確認（SELECT COUNT）
     */
    public int idCheck(String userId);

    /*
      パスワード照合用（未使用の可能性あり）
     */
    public int pwCheck(String userPw);

    /*
      ログイン処理（IDとPWによる照合）
     */
    public UserVO userLogin(UserVO user);

    /*
      ユーザーID検索（名前・メールに基づく）
     */
    public UserVO searchId(UserVO vo) throws Exception;

    /*
      ユーザーの登録メールアドレス取得
     */
    public String findEmail(String userid) throws Exception;

    /*
      パスワード更新（仮パスワード再設定）
     */
    @Transactional
    public int updatePw(UserVO vo) throws Exception;

    /*
      パスワード取得（ユーザーID基準）
     */
    public String userPw(String userId);

    /*
      ユーザー情報取得（ユーザーID基準）
     */
    public UserVO user(String userId);

    /*
      ユーザー情報更新
     */
    public int updateInfo(UserVO user);

    /*
      ユーザー削除（退会処理）
     */
    public int delete(String userId);
}
