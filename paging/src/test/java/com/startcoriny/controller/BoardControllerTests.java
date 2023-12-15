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
@WebAppConfiguration // Servlet�� ServletContext�� �̿��ϱ� ���ؼ� ��������� ������������ WebApplicationContext��� ���縦 �̿��ϱ� ���ؼ� ����Ѵ�.
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_= @Autowired)
	private WebApplicationContext ctc;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() { //import�Ҷ� junit�� �̿��ϸ� @before�� ����� �޼���� ��� �׽�Ʈ ���� �Ź� ����Ǵ� �޼��尡 �ȴ�. 
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctc).build();// ��¥ mvc
																		// url�� �Ķ���͵��� ���������� ����ϴ� ��ó�� ���� Controller���� ������ ���� �ִ�.
		
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
//				.param("title", "�׽�Ʈ ���� ����")
//				.param("content", "�׽�Ʈ ���� ����")
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
//				.param("title","������ �׽�Ʈ ���� ����")
//				.param("content","������ �׽�Ʈ ���� ����")
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
