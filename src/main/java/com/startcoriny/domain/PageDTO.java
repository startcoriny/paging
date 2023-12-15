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
		
		this.endPage = (int) (Math.ceil(cri.getPageNum()/5.0))*5; // pagenum�� 10�� �Ѿ�� ������ ceil�Լ��� ���� 1/10=0.1, 2/10=0.2 ...10/10=1 ��ΰ� 1�̱� ������ �������� �Űܵ� 11���� ���ڷ� �Ѿ�� ����.
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
