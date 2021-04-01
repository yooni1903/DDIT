<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/inlcudeDesc.jsp</title>
</head>
<body>
<h4>내포(include)의 종류</h4>
<pre>
	언제 include 되느냐, 무엇을 include 하느냐
	1. 동적 include : runtime에 실형 결과를 내포한다.		(application이 동작되고 있는 중에 실행된다.)
		: RequestDispatcher.include
		: pageContext.include
		: include 액션 태그를 사용
		<jsp:include page="/04/localeDesc.jsp"/>
		커스텀 태그 : 개발자가 필요에 의해서 새로 정의한 태그이다.
		&lt;prefix:tagname attributes /&gt;	(prefix : 접두어) 
		
	2. 정적 include : 소스가 파싱될 때, 소스 자체를 내포한다. (가져온다.)
		<%--@ include file="/04/localeDesc.jsp" --%>
		<%--=locale --%>
</pre>
</body>
</html>