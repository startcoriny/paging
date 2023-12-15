package com.startcoriny.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startcoriny.domain.BoardVO;
import com.startcoriny.domain.Criteria;
import com.startcoriny.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_= @Autowired)
	private BoardMapper mapper;

	
	// 게시글 리스트 및 페이징
	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		log.info("get List with criteria: " + cri);
		
		return mapper.getListWithPaging(cri);
	}
	
	
	// 게시물 읽기
	@Override
	public BoardVO get(Long bno) {
		log.info("get....." + bno);
		return mapper.read(bno);
	}

	
	// 게시물추가
	@Override
	public void register(BoardVO board) {
		
		log.info("register....." + board);
		
		mapper.insertSelectKey(board);
	}

	
	// 게시물 수정
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify........" + board);
		return mapper.update(board) == 1;
	}

	
	// 게시물 삭제
	@Override
	public boolean remove(Long bno) {
		log.info("remove...." + bno);
		return mapper.delete(bno) == 1;
	}

	
	// 총 게시물 갯수
	@Override
	public int getTotal(Criteria cri) {
		
		log.info("get total count");
		
		return mapper.getTotalCount(cri);
	}

//	@Override
//	public List<BoardVO> getList() {
//		
//		log.info("getList...........");
//		return mapper.getList();
//	}
	
	
}
