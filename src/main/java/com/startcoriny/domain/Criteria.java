package com.startcoriny.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria { // 용도 : pageNum과 amount 값을 같이 전달하는 용도

	private int pageNum;
	private int amount;
	
	public Criteria() { //기본값 : 1페이지, 10개의 게시물
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		
		this.pageNum = pageNum;
		this.amount = amount;
		
	}
	
}
