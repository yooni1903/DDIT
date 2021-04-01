<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flowControl.jsp</title>
</head>
<body>
<h4>웹 어플리케이션에서 흐름 제어 방법(페이지 이동)</h4>
<pre>
1. 서버 내에서의 위임 구조, RequestDispatcher(분기 제어 관리자) 사용
	- 원본 요청(request)을 도착점에서 사용할 수 있는 구조.
	1) forward
	2) include
	<%--
		// 서버 사이드 절대경로에는 이미 contextPath에 대한 정보를 알고 있기 때문에 기술할 필요가 없다.
		String dest = "/04/localeDesc.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.include(request, response);
	--%>
	
	
2. Redirect : Body가 없이, 상태코드(302)로 구성된 line과 location 헤더를 가지고 응답이 전송된다.
			- HTTP의 Stateless 특성에 따라 원본 요청에 대한 정보는 사라짐
	<%
		String location = request.getContextPath() + "/04/localeDesc.jsp";
		response.sendRedirect(location);
	
	%>
</pre>
</body>
</html>