package com.startcoriny.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int) (Math.ceil(cri.getPageNum()/5.0))*5; // pagenum이 10을 넘어가지 않으면 ceil함수로 인해 1/10=0.1, 2/10=0.2 ...10/10=1 모두가 1이기 때문에 페이지를 옮겨도 11번의 숫자로 넘어가지 않음.
		System.out.println("@@@@@@@this.endPage = " + this.endPage);// 10..20..30..40..50
		this.startPage = this.endPage - 4; // 1..11..21..31..41
		
		int realend = (int) Math.ceil((total * 1.0)/cri.getAmount());
		
		if(realend < this.endPage){
			this.endPage = realend;
		}
		
		this.prev = this.startPage > 1; 
		this.next = this.endPage < realend;
		
	}
}
