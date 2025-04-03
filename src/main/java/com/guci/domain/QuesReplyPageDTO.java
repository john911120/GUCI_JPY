package com.guci.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class QuesReplyPageDTO {
	private int replyCnt;
	private List<QuesReplyVO> list;
}
