<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<%
  	MemberVO memVo = (MemberVO)request.getAttribute("memberVo");
%>
<script type="text/javascript">
  $(function(){
	  // 회원 목록 버튼 클릭 처리
	  $('#memListBtn').on('click', function(){
		 location.href="<%=request.getContextPath()%>/member/memberList.ddit";
	  });
	  
	  // 수정 버튼 클릭처리
	  $('#updateBtn').on('click', function(){
		  // Form의 객체를 찾아야 함 - action 속성 이용하려고
		  // Form 객체 찾는 방법1) dom객체 이용
		  var memForm = document.getElementById("memberForm");
	  	  alert(memForm);
	  	  memForm.action = "<%=request.getContextPath()%>/member/memberUpdateForm.ddit";
	  	  memForm.submit();	// memForm을 서버로 전송해줘야 함
		  
	  });
	  
	  // 삭제 버튼 클릭 처리
	  $('#deleteBtn').on('click', function(){
		  // Form 객체 찾는 방법2) jQuery 이용
		 var memForm = $('#memberForm').get(0);
	  	 memForm.action = "<%=request.getContextPath()%>/member/memberDelete.ddit";
	  	 memForm.submit();	// memForm을 서버로 전송해줘야 함
	  
	  });
	  
  });

</script>
</head>
<body>
  <h2>회원 정보 상세 보기</h2>
  <form id="memberForm" method="post"> <!-- action 안 넣으면 원래 위치로 -->	
    <input type="hidden" name="mem_id" value="<%=memVo.getMem_id() %>">
    <!-- 수정이나 삭제작업을 할 때 이 아이디 값을 갖고와서 처리하기 위해서 -->
    <table border="1">
      <tr>
        <td>회원ID</td>
        <td><%=memVo.getMem_id() %></td>
      </tr>
      
      <tr>
        <td>회원이름</td>
        <td><%=memVo.getMem_name() %></td>
      </tr>
      
      <tr>
        <td>전화번호</td>
        <td><%=memVo.getMem_tel() %></td>
      </tr>
      
      <tr>
        <td>회원주소</td>
        <td><%=memVo.getMem_addr() %></td>
      </tr>
      
      <tr>
        <td colspan="2">
       	  <input type="button" value="수정" id="updateBtn">
       	  <input type="button" value="삭제" id="deleteBtn">
       	  <input type="button" value="회원목록" id="memListBtn">
        </td>
      </tr>
    </table>
  </form>
</body>
</html>