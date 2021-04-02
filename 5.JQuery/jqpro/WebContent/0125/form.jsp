<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
	p{
		font-size: 2.0em;
		color: red;
	}
</style>
</head>
<body>
    <h1>JSP : Java Server Page</h1>
    <%
    	String aa = request.getParameter("txt");
    	String bb = request.getParameter("pass");
    %>
    
    <p><%= aa %></p> <br />
    <p><%= bb %></p> <br />
</body>
</html>