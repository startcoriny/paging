package com.startcoriny.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria { // �뵵 : pageNum�� amount ���� ���� �����ϴ� �뵵

	private int pageNum;
	private int amount;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		
		this.pageNum = pageNum;
		this.amount = amount;
		
	}
	
}
