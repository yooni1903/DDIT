<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session 연습</title>
</head>
<body>
<%
// JSP문서에는 세션 객체가 'session'이라는 이름으로 자동 저장되어 있다.
	out.println("testSession 세션값 : " + session.getAttribute("testSession") + "<br>");
%>

USERNAME : <%=session.getAttribute("userName") %> <br>
AGE : <%=session.getAttribute("age") %> <br>
<hr>

	<a href="<%=request.getContextPath()%>/sessionAddServlet.do">Session정보 저장하기</a> <br><br>
	
	<a href="<%=request.getContextPath()%>/sessionReadServlet.do">저장된 Session정보 확인하기</a> <br><br>
	
	<a href="<%=request.getContextPath()%>/sessionDeleteServlet.do">저장된 Session정보 삭제하기</a> <br><br>
</body>
</html>