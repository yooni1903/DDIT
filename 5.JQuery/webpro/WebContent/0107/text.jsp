<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	div{
		border : 2px solid pink;
		margin : 10px;
		padding : 10px;
		height : auto;
	}
</style>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String inputText = request.getParameter("area");
		
		//inputText의 엔터값(\r\n)에서 줄 바꾸기 <br> - replaceAll('old', 'new')
		
		String text = inputText.replaceAll("\r", "").replaceAll("\n", "<br>");
	%>
	
	<p>입력 내용을 request로 가져와 출력할 때 줄바꿈은 무시하고 출력한다,</p>
	
	<div><%= inputText %></div>
	
	<br>
	<p>입력 내용을 request로 가져와 출력할 때도 여러 줄로 입력된 내용과 같은 형태로 출력한다,</p>
	<div><%= text %></div>

</body>
</html>