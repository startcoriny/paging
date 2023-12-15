package com.startcoriny.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // Servlet의 ServletContext를 이용하기 위해서 사용하지만 스프링에서는 WebApplicationContext라는 존재를 이용하기 위해서 사용한다.
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_= @Autowired)
	private WebApplicationContext ctc;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() { //import할때 junit을 이용하면 @before가 적용된 메서드는 모든 테스트 전에 매번 실행되는 메서드가 된다. 
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctc).build();// 가짜 mvc
																		// url과 파라미터등을 브라우저에서 사용하는 것처럼 만들어서 Controller에서 실행해 볼수 있다.
		
	}
	
//	@Test
//	public void testList() throws Exception {
//		
//		log.info(
//				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
//				.andReturn()
//				.getModelAndView()
//				.getModelMap());
//	}
	
//	@Test
//	public void testRegister() throws Exception{
//		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
//				.param("title", "테스트 새글 제목")
//				.param("content", "테스트 새글 내용")
//				.param("writer", "user00")
//				).andReturn().getModelAndView().getViewName();
//		
//		log.info(resultPage);
//		
//	}

//	@Test
//	public void testGet() throws Exception{
//		
//		log.info(mockMvc.perform(MockMvcRequestBuilders
//				.get("/board/get")
//				.param("bno","6"))
//				.andReturn()
//				.getModelAndView().getViewName());
//		
//	}
	
//	@Test
//	public void testModify() throws Exception{
//		
//		String resultPage = mockMvc
//				.perform(MockMvcRequestBuilders.post("/board/modify")
//				.param("bno","1")
//				.param("title","수정된 테스트 새글 제목")
//				.param("content","수정된 테스트 새글 내용")
//				.param("writer","user00"))
//				.andReturn()
//				.getModelAndView().getViewName();
//		log.info(resultPage);
//	}

	
//	@Test
//	public void testRemove() throws Exception{
//		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
//				.param("bno", "11")
//				).andReturn().getModelAndView().getViewName();
//	
//		log.info(resultPage);
//		
//	}

	@Test
	public void testListPaging() throws Exception{

		
		log.info(mockMvc.perform(
			MockMvcRequestBuilders.get("/board/list")
			.param("pageNum", "2")
			.param("amount", "50"))
			.andReturn().getModelAndView().getModelMap());
		
		
		
	}
	
	
}
