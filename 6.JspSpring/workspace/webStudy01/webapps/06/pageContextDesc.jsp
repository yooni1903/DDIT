<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/06/PageContextDesc.jsp</title>
</head>
<body>
<h4>PageContext</h4>
<pre>
	: 하나의 JSP에 대한 모든 정보를 가진 객체
	: 가장 먼저 생성되는 기본객체로 나머지 기본객체에 대한 참조를 소유함
	<%=request==pageContext.getRequest() %> --> 주소값 비교
	<%=pageContext.getRequest() instanceof HttpServletRequest %>
	<%=((HttpServletRequest) pageContext.getRequest()).getRequestURI() %> -> 페이지 컨텍스트만 있으면 URI를 받을 수 있다.
	<%=session==pageContext.getSession() %>
	<%=application==pageContext.getServletContext() %>
	${pageContext.request.contextPath }
	
	1. 영역(scope) 제어
	2. 에러 데이터 확보
	3. 페이지 이동(dispatch 방식의 이동 - forward, include)
	<%
		String relativeUrlPath = "/04/localeDesc.jsp";
// 		pageContext.forward(relativeUrlPath);
		pageContext.include(relativeUrlPath);
// 		request.getRequestDispatcher(relativeUrlPath).include(request, response);
	%>
	인클루드 이후의 텍스트
</pre>
</body>
</html>