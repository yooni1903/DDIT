<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script>
	$(function(){
		$('#memListBtn').on('click', function(){
			location.href="<%=request.getContextPath()%>/member/memberList.ddit";
		});
		
		// 수정 버튼 클릭 처리
		$('#updateBtn').on('click', function(){
			location.href="<%=request.getContextPath()%>/member/memberUpdate.ddit"
		});
		
		// 삭제 버튼 클릭 처리
		$('#deleteBtn').on('click', function(){
			
		});
		
	});
</script>
</head>
<body>
	<h2>회원 정보 상세 보기</h2>
	<%
	    MemberVO memVo = (MemberVO) request.getAttribute("memberVo");
	%>
	<form action="" id="memberForm" method="post">
		<input type="hidden" name="mem_id" value="<%=memVo.getMem_id()%>">
	    <table border="1">
            <tr>
                <td>회원ID</td>
                <td><%=memVo.getMem_id()%></td>
            </tr>
            <tr>
                <td>회원이름</td>
                <td><%=memVo.getMem_name()%></td>
            </tr>
            <tr>
                <td>전화번호</td>
                <td><%=memVo.getMem_tel()%></td>
            </tr>
            <tr>
                <td>회원주소</td>
                <td><%=memVo.getMem_addr()%></td>
            </tr>
            <tr>
                <td colsapn="2">
                    <input type="button" value="수정" id="updateBtn">
                    <input type="button" value="삭제" id="deleteBtn">
                    <input type="button" value="회원목록" id="memListBtn">
                </td>
            </tr>
        </table>
	</form>
</body>
</html>