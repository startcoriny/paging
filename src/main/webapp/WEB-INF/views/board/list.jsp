<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board List Page
                           	<button id="regBtn" type="button" class="btn btn-xs pull-right">Register New Board</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${list }" var="board">
                                <tr>
                                	<td><c:out value="${board.bno }"/></td>
                                	<td><a class="move" href='<c:out value="${board.bno }"/>'> <!-- a태그와 form태그에는 속성을 target=_blank로 지정하면 새창을 통해서 링크이동을 한다. -->
                                		<c:out value="${board.title }"/>
                                		</a>
                                	</td>
                                	<td><c:out value="${board.writer }"/>
                                	
                                	</td>
                                	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /> </td>
                                	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}" /> </td>
                                </tr>
                                </c:forEach>
                            </table>
							<!-- /table -->
							
							<!-- start Pagination -->
							<div class="pull-right">
								<ul class="pagination">
									
									<c:if test="${pageMaker.prev }"> <!-- startPage가 1보다 크다면 조건문 발동 -->
										<li	class="paginate_button previous">
											<a href="${pageMaker.startPage-1 }">Previous</a> <!-- startPage가 1보다 크면 11, 11 - 10으로 이전페이지로 이동 -->
										</li>
									</c:if>
								
									<c:forEach	var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }"> <!-- num이라는 변수명으로 startPage(1)에서 endPage(10)까지 1씩 증가하면서 생성 -->
										<li class="paginate_button ${pageMaker.cri.pageNum == num?'active':'' }" >
											<a href="${num }">
												${num }
											</a>
										</li>
									</c:forEach>
								
									<c:if test="${pageMaker.next }">
										<li	class="paginate_button next">
											<a href="${pageMaker.endPage +1 }">Next</a>
										</li>
									</c:if>
								</ul>
							</div>
							<!-- end Pagination -->
							
							<!-- 페이지의 데이터를 보내기 위한 form -->
							<form action="/board/list" id="actionForm" method="get">
								<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }"> <!-- 자바스크립트에서 동적으로 바뀌도록 설정했으므로 value생략 가능 -->
								<input type="hidden" name="amount" value="${pageMaker.cri.amount }"> <!-- 자바스크립트에서 pageNum은 다루므로 생략 가능하지만 amount는 다루지않으므로 value 초기값 필요 -->
							</form>
							<!-- 페이지의 데이터를 보내기 위한 form end -->
							
							
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
												&times;
											</button>
											<h4 class="modal-title" id="myModalLabel">Modal title</h4>
										</div>
										<div class="modal-body">처리가 완료되었습니다.</div>
										<div class="modal-footer ">
											<button type="button" class="btn btn-default" data-dismiss="model">Close</button>
											<button type="button" class="btn btn-primary">Save changes</button>
										</div>
									</div>
									<!-- /.modal-content  -->
								</div>
								<!-- /.modal-dialog  -->
							</div>
							<!-- /.modal -->			
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
<script type="text/javascript">
	$(document).ready(function(){
		var result = '<c:out value="${result}"/>';
		
		checkModal(result); // 
		
		history.replaceState({},null,null);
		
		function checkModal(result){ // 파라미터에 따라서 모달창을 보여주거나 내용을 수정한 뒤 보이도록 작성.
			
			if(result === '' || history.state){ //history.state는 자바 스크립트에서 제공하는  historyAPI로서 현재 세션 히스토리 항목의 상태 객체를 나타낸다.
				return;
			}
			
			if(parseInt(result) > 0){ // 새로운 게시글이 작성되는 경우 RedirectAttributes로 게시물의 번호가 전송되므로 이를 이용해서 모달창의 내용을 수정.
				$(".modal-body").html("게시글" + parseInt(result)+"번이 등록되었습니다.");
			}
			
			$("#myModal").modal("show");
		}
		
		$("#regBtn").on("click",function(){
			self.location = "/board/register";
		});
		
		var actionForm = $("#actionForm");
		
		$(".paginate_button a").on("click", function(e){
			
			e.preventDefault();
			
			console.log('click');
			
 			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
		
		
		$(".move").on("click",function(e){
			
			e.preventDefault();
			
			actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'> ");
			
			actionForm.attr("action","/board/get");
			actionForm.submit();
		});
		
	});
</script>
            
<%@include file="../includes/footer.jsp" %>
