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
			
// 			Cookie allDomain = new Cookie("allDomainCookie", "all domain");
			// tomcat 8.5버전에서는 이런 도메인 설정이 불가능 하다
			// 확인하려면 7버전으로 갈것!
// 			allDomain.setDomain(".kdy.com");
// 			response.addCookie(allDomain);
			
			Cookie allPathCookie = new Cookie("allPathCookie", "All~Path~");
			
			// 이 Path는 클라이언트쪽의 브라우저가 사용하는 것이기 때문에
			// 클라이언트가 사용할 수 있는 reqeust.getContextPath를 사용해야한다.
			// 그런데 만약 tomcat8.5안에 구동되고 있는 application이 두 개였을 때 이 두개 사이에서
			// 세션을 공유하고 싶다면, localhost 아래에 존재하는 모든 application에 접근할 수 있도록
			// path를 설정해주면 되는데 이때는 "/"이 해당한다. (최상위 도메인으로 간다는 것)
			allPathCookie.setPath("/");
			response.addCookie(allPathCookie);
			
			Cookie longLiveCookie = new Cookie("longLiveCookie", "Long~~");
			longLiveCookie.setMaxAge(-1);
			longLiveCookie.setPath(request.getContextPath());
			response.addCookie(longLiveCookie);
		%>
		
		(서버사이드 기준)
		1. 쿠키 생성
		2. 응답에 쿠키를 포함하여 전송(Response Header 쿠키가 서버에서 만들어졌기 때문에 클라이언트로 보내야한다.)
		
		(클라이언트 사이드)
		3. 쿠키가 브라우저가 가진 저장소에 저장(한 클라이언트가 여러 개의 브라우저를 사용하고 있다면 저장소도 여러개)
		4. 저장되어 있던 쿠키가 다음 번 요청에 실려 재전송된다.(Request Header)
		
		(다시 서버 사이드)
		5. 요청에 포함된 쿠키를 꺼내서 상태를 복원한다.
		<a href="cookieView.jsp">쿠키 확인하기(동일경로)</a>
		<a href="../08/cookieView.jsp">쿠키 확인하기(다른경로)</a>
		
		
		
		** 쿠키 속성의 종류
		1. name(required) : 공백포함X, 특수문자X, _사용가능 (자바에서 사용하는 변수 선언 규칙과 동일)
		2. value(required) : 특수문자를 포함하는 경우, RFC2396 규약에 따라 url encoding 방식을 사용한다.(복원시-decoding)
		
		-----optional
		3. domain(host) : 쿠키의 재전송을 결정하는 조건
			생략시, 쿠키 생성 도메인이 기본값으로 반영됨
			ex) .naver.com으로 설정 되었다면, host에 상관없이 재전송 가능
		4. path : 쿠키의 재전송을 결정하는 조건
			다음번 요청이 설정된 경로 이하로 발생할 때만 재전송
			생략 시, 쿠키 생성시의 경로가 기본 값으로 반영됨.
		5. maxAge(expires) : 쿠키의 만료 시점 (second 단위)
			생략 시, 기본값으로 세션과 동일한 만료 시점이 사용.
			0 : 쿠키 삭제(쿠키의 모든 조건이 동일한 경우에만 적용된다.)
			-1 : 브라우저 종료 시에 쿠키를 삭제한다.
		6. httpOnly : http 프로토콜에만 재전송하겠다는 의미
		7. secure : https 프로토콜에만 재전송하겠다는 의미
		
</pre>
</body>
</html>