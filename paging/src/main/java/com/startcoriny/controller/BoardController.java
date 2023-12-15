package com.startcoriny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.startcoriny.domain.BoardVO;
import com.startcoriny.domain.Criteria;
import com.startcoriny.domain.PageDTO;
import com.startcoriny.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	
	private BoardService service;
	
	// �Խù� ����Ʈ
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list : " + cri);
		
		int total = service.getTotal(cri);
		
		model.addAttribute("list",service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	
	// �Խù� �߰�
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) { // �߰������� ���Ӱ� ��ϵ� �Խù��� ��ȣ�� ���� �����ϱ� ���ؼ� RedirectAttributes�� ����Ѵ�.
																	 // why? redirect�� ����ؼ� �������� ������ �ϰ� �Ǵµ� ���� �������� ���� �ʰ� �Ǹ� ���ΰ�ħ�� ���ؼ� ������ ������ ������ ����ؼ� ����Ҽ� �ֱ� ������ ������ �߻��Ѵ�. �̸� �ذ��ϱ� ���ؼ� ���, ����, ���� �۾�ó���� �Ϸ�� �� ������ ������ �����Ҽ� ������ �������� URL�� �̵��ϴ� ����� �̿��ϰ� ����� �ٷ� �˼� �ְ� �ǵ�� ����� �Ѵ�.
		log.info("register: " + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno()); //addFlashAttribute - ��ȸ�����θ� ������ ����.(HttpSession�� ���ؼ�)
		return "redirect:/board/list"; //��� �۾��� ������ �ٽ� ���ȭ������ �̵��ϱ� ���� redirect
	}

	// �Խù� �߰� �Է� ������
	@GetMapping("/register") //�Է��������� �����ֱ� ���� ����
	public void register() {
		
	}
	
	// �Խù� �� ���� ������ â �̵�
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get of modofy");
		model.addAttribute("board", service.get(bno));
	}
	
	
	// �Խù� ����
	@PostMapping("/modify")
	public String modify(BoardVO board,@ModelAttribute("cri") Criteria cri , RedirectAttributes rttr) {
		
		log.info("modify: " + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success"); 			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list"; //��� �۾��� ������ �ٽ� ���ȭ������ �̵��ϱ� ���� redirect
	}

	
	//�Խù� ����
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno,@ModelAttribute("cri") Criteria cri , RedirectAttributes rttr) {
		
		log.info("remove.... " + bno);
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success"); 			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list"; //��� �۾��� ������ �ٽ� ���ȭ������ �̵��ϱ� ���� redirect
	}

}
