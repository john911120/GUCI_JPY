package com.guci.domain;

import java.util.Date;

import lombok.Data;

@Data
public class UserVO {

    private String userId,userPw,userName,userPhone,userEmail,userAddr1,userAddr2,userAddr3;
    private Date regDate;
    private Long sum, stock;

}
