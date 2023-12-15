package com.startcoriny.mapper;

import java.util.List;

import com.startcoriny.domain.BoardVO;
import com.startcoriny.domain.Criteria;

public interface BoardMapper {

	//@Select("select * from tbl_board where bno > 0")
	// └ xml에 sql문이 처리되어 인터페이스에 적힌 sql제거.
	
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);

	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public BoardVO read(Long bno);
	
	public void insertSelectKey(BoardVO board);
	
	public int update(BoardVO board);
	
	public int delete(Long bno);
	
	public int getTotalCount(Criteria cri);
	
}
