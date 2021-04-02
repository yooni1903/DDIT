<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSP : Java Server Page</h1>
	<%
		request.setCharacterEncoding("UTF-8");	// 변수에 한글이 들어오게 되면 반드시 해줘야한다.
	
		String userId = request.getParameter("id");
		String userName = request.getParameter("name");
		String userTel = request.getParameter("tel");
		String userPass = request.getParameter("pass");
		
	%>
	
	<p><%= userId %>님 환영합니다!</p>
	<p><%= userName %>님 즐거운 하루 되세요!</p>
	<p><%= userTel %>전화번호!</p>
	<p><%= userPass %>비밀번호!</p>
	
	
</body>
</html>