<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/sessionDesc.jsp</title>
</head>
<body>
<h4>HttpSession</h4>
<pre>
	: 한 세션에 대한 정보를 가진 객체.
	세션이란?
	통로 : 클라이언트와 서버 사이의 유일하 ㄴ연결통로.
	기간(시간) : 하나의 클라이언트가 하나의 에이전트를 이용해서 서버사이드어플을 사용하기 시작한 순간(최초의 요청)
			사용 종료 이벤트가 종료가 발생할 때까지의 기간
	session lifecycle
	생성 : 최초의 요청임이 식별되는 요청이 발생했을 때
			-> 세션 하나에 식별자로 ID가 부여됨
			<%=new Date(session.getCreationTime()) %>
			<%=session.getId() %>
			<%=new Date(session.getLastAccessedTime()) %>
			<%=session.getMaxInactiveInterval() %>
	종료
		1) 명시적인 로그아웃
		2) 브라우저 종료
		3) 쿠키 삭제
		4) timeout 이내에 새로운 요청이 발생하지 않을 때 -> 일정 시간(timeout) 이후 사라지게 되어있다.
		
	쿠키의 트래킹 모드(tracking mode)
		1) Cookie : JSESSIONID이름의 쿠키를 통해 세션 아이디가 재전송되는 구조
		2) URL : jsessionid라는 이름의 세션 파라미터를 요청 라인을 통해 재전송하는 구조
		<a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">세션 파라미터를 통해 세션 유지</a>
		3) SSL : Secure Socket Layer를 통해 암호화된 식별자가 재전송되는 구조
		
</pre>
</body>
</html>