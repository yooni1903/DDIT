<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/applicationDesc.jsp</title>
</head>
<body>
<h4>ServletContext</h4>
<pre>
	: 하나의 웹 어플리케이션과 해당 앱이 운영되는 서버에 대한 정보를 가진 객체
	
	1. 서버의 정보 확인
		<%=application.getServerInfo() %>
		<%=application.getMajorVersion() + "." + application.getMinorVersion() %>
	2. 서버와의 통신
		<%=application.getMimeType("test.jpg") %>
	3. 컨텍스트 파라미터 확보
		<%=application.getInitParameter("contentFolder") %>
		servletContext's hashcode : <%=application.hashCode() %> , <%=getServletContext().hashCode() %>	
								-> application에서 가져오든 ServletContext에서 가져오든 hashCode는 동일하다 -> 같은 객체를 사용한다.
	4. 로그 기록
		<%
			application.log("로그 메시지 기록");
		%>
	5. ***** 웹 리소스 확보
		file system path = getResourcePath(resource URL - server side 방식)
		URL = getResource(resource URL - server side 방식)
		inputStream = getResourceAsStream(resource URL - server side 방식)
		
</pre>
</body>
</html>