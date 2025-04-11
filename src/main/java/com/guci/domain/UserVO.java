package com.guci.domain;

import java.util.Date;

import lombok.Data;

@Data
public class UserVO {
/*
    ユーザーID
    private String userId;
    
    ユーザー暗証番号
    private String userPw;
    
    ユーザー名前
    private String userName;
    
    ユーザー携帯電話
    private String userPhone;
    
    ユーザーメールアドレス
    private String userEmail;
    
    ユーザー住所1「日本の都道府県」
    private String userAddr1;
    
    ユーザー住所2「日本の市区町村」
    private String userAddr2; 
    
    ユーザー住所3「日本の番地、号、建物名など」
    private String userAddr3;
    
    メンバー加入日
    private Date regDate;
    
    カートに入った商品の総金額
    private Long sum;
    
    カートに入った商品の在庫
    private Long stock;
    
*/    

    private String userId;
    private String userPw;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userAddr1;
    private String userAddr2; 
    private String userAddr3;
    
    private Date regDate;
    
    private Long sum;
    private Long stock;
}
