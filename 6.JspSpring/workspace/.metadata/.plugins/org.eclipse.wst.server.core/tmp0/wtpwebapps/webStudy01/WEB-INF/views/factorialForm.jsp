<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>팩토리얼 연산 처리</h4>
동기처리 연산수행
비동기 처리 연산수행(결과값을 한번은 json, html로 내보내본다.)
그렇다면 동기로 처리할건지 비동기로 처리할건지 선택해야하는 ui를 제공해야한다

<%--
	 Map<String,Object> target = (Map) request.getAttribute("target");
--%>

<form method="post">
	<input type="number" name="single" value="${target.op}"/>
	<input type="submit" value="=" />
	${target["result"] }
</form>
</body>
</html>