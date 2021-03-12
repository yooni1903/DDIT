<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>forward, sendRedirect 연습</h2>
    <hr>
    
    <form action="<%=request.getContextPath()%>/responseTest01.do" method="post">
        forward action : <input type="text" name="username">
        <input type="submit" value="확인">
    </form>
    
    <form action="<%=request.getContextPath()%>/responseTest02.do" method="post">
        response.sendRedirect : <input type="text" name="username">
        <input type="submit" value="확인">
    </form>
</body>
</html>