<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <a href="<%=request.getContextPath()%>/cookieCountServlet.do">Cookie count 증가</a><br><br>
    <a href="<%=request.getContextPath()%>/cookieCountDelServlet.do">Cookie count 초기화</a><br><br>
</body>
</html>