<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- useBean의 특성 받아오는 객체가 null이라면 강제로 비어있는 객체를 생성하기 때문에 view에서는 null 문자가 뜬다. 
	-> null Point를 한번 막으려는 것
 -->
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean>
<h4><%=member.getMem_name() %>님의 마이페이지</h4>
<table>
		<tr>
			<th>회원 아이디</th>
			<td><%=member.getMem_id()%></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><%=member.getMem_pass()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=member.getMem_name()%></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><%=member.getMem_regno1()%></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><%=member.getMem_regno2()%></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><%=member.getMem_bir()%></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><%=member.getMem_zip()%></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><%=member.getMem_add1()%></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><%=member.getMem_add2()%></td>
		</tr>
		<tr>
			<th>집 전화번호</th>
			<td><%=member.getMem_hometel()%></td>
		</tr>
		<tr>
			<th>회사 전화번호</th>
			<td><%=member.getMem_comtel()%></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><%=member.getMem_hp()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=member.getMem_mail()%></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><%=member.getMem_job()%></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><%=member.getMem_like()%></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><%=member.getMem_memorial() %></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><%=member.getMem_memorialday() %></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=member.getMem_mileage() %></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><%=member.getMem_delete() %></td>
		</tr>
	</table>











<%-- <% --%>
<!-- // 	MemberVO member = (MemberVO) session.getAttribute("authMember"); -->
<!-- // 	if(member != null){ -->
<%-- %> --%>
<%-- 	<h4><%=member.getMem_name() %>님의 마이페이지</h4> --%>
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<th>아이디</th> -->
<%-- 			<td><%=member.getMem_id() %></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<th>주소</th> -->
<%-- 			<td><%=member.getMem_add1()+" "+member.getMem_add2() %></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<th>생일</th> -->
<%-- 			<td><%=member.getMem_bir() %></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<th>집 전화번호</th> -->
<%-- 			<td><%=member.getMem_hometel()%></td> --%>
<!-- 		</tr> -->
<!-- 	</table> -->
<%-- <%} %> --%>
</body>
</html>