<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr>
	<h2>Request연습 Form (숫자 입력은 정수형으로 입력하세요.)</h2>
	<form name="testForm" method="post" action="<%=request.getContextPath()%>/requestTest02.do">
	<input type="text" size="10" name="first">
	<select name="cal">
		<option value="+">+</option>
		<option value="-">-</option>
		<option value="*">*</option>
		<option value="/">/</option>
		<option value="%">%</option>
	</select>
	<input type="text" size="10" name="second">
	<input type="submit" value="확인">
	
	</form>

</body>
</html>