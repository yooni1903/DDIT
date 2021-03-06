<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/cookieDesc.jsp</title>
</head>
<body>
<h4>Cookie</h4>
<pre>
	: Http의 stateless 특성을 보완하기 위해 최소한의 상태 정보를 저장하는 개념
		1. session : 상태 정보를 서버 사이드에서 유지.
		2. cookie : 상태 정보를 클라이언트 사이드에서 유지.
		
		** 쿠키 사용 방법
		<%
			Cookie sampleCookie = new Cookie("sampleCookie", "CookieValue");
			response.addCookie(sampleCookie);
			
			String koreanValue = URLEncoder.encode("한글쿠키", "UTF-8");
			
			Cookie koreanCookie = new Cookie("koreanCookie", koreanValue);
			response.addCookie(koreanCookie);
			
			Cookie allDomain = new Cookie("allDomainCookie", "all domain");
			allDomain.setDomain(".kdy.com");
			response.addCookie(allDomain);
		%>
		
		(서버사이드 기준)
		1. 쿠키 생성
		2. 응답에 쿠키를 포함하여 전송(Response Header 쿠키가 서버에서 만들어졌기 때문에 클라이언트로 보내야한다.)
		
		(클라이언트 사이드)
		3. 쿠키가 브라우저가 가진 저장소에 저장(한 클라이언트가 여러 개의 브라우저를 사용하고 있다면 저장소도 여러개)
		4. 저장되어 있던 쿠키가 다음 번 요청에 실려 재전송된다.(Request Header)
		
		(다시 서버 사이드)
		5. 요청에 포함된 쿠키를 꺼내서 상태를 복원한다.<a href="cookieView.jsp">쿠키 확인하기(동일경로)</a>
		
		
		
		** 쿠키 속성의 종류
		1. name(required) : 공백포함X, 특수문자X, _사용가능 (자바에서 사용하는 변수 선언 규칙과 동일)
		2. value(required) : 특수문자를 포함하는 경우, RFC2396 규약에 따라 url encoding 방식을 사용한다.(복원시-decoding)
		
		-----optional
		3. domain(host) 
		4. path
		5. maxAge(expires)
		6. httpOnly
		7. secure
		
</pre>
</body>
</html>