package com.guci.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class QuesReplyPageDTO {
	// Q&Aの質問に対するコメントの数
	private int replyCnt;
	
	// Q&Aの質問に対するコメントのリスト
	private List<QuesReplyVO> list;
}
