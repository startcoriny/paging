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
	
	// 게시물 리스트
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list : " + cri);
		
		int total = service.getTotal(cri);
		
		model.addAttribute("list",service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	
	// 게시물 추가
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) { // 추가적으로 새롭게 등록된 게시물의 번호를 같이 전달하기 위해서 RedirectAttributes를 사용한다.
																	 // why? redirect를 사용해서 브라우저로 재전송 하게 되는데 만약 재전송을 하지 않게 되면 새로고침을 통해서 동일한 내용을 서버에 계속해서 등록할수 있기 때문에 문제가 발생한다. 이를 해결하기 위해서 등록, 수정, 삭제 작업처리가 완료된 후 동일한 내용을 전송할수 없도록 브라우저의 URL로 이동하는 방식을 이용하고 결과를 바로 알수 있게 피드백 해줘야 한다.
		log.info("register: " + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno()); //addFlashAttribute - 일회성으로만 데이터 전달.(HttpSession을 통해서)
		return "redirect:/board/list"; //등록 작업이 끝난후 다시 목록화면으로 이동하기 위한 redirect
	}

	// 게시물 추가 입력 페이지
	@GetMapping("/register") //입력페이지를 보여주기 위한 매핑
	public void register() {
		
	}
	
	// 게시물 및 수정 페이지 창 이동
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get of modofy");
		model.addAttribute("board", service.get(bno));
	}
	
	
	// 게시물 수정
	@PostMapping("/modify")
	public String modify(BoardVO board,@ModelAttribute("cri") Criteria cri , RedirectAttributes rttr) {
		
		log.info("modify: " + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success"); 			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list"; //등록 작업이 끝난후 다시 목록화면으로 이동하기 위한 redirect
	}

	
	//게시물 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno,@ModelAttribute("cri") Criteria cri , RedirectAttributes rttr) {
		
		log.info("remove.... " + bno);
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success"); 			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list"; //등록 작업이 끝난후 다시 목록화면으로 이동하기 위한 redirect
	}

}
