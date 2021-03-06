<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/cacheControl.jsp</title>
</head>
<body>
<h4>캐시 제어</h4>
<pre>
	캐시 제어를 위한 응답 헤더
	1) Cache-Control(Http 1.1) : public(공용 캐시로 저장, gateway 뒤에 있는 프록시 서버에 저장),
								 private(자신의 브라우저에 캐시 저장),
								 no-cache(no-store) -> 캐시 저장 안함
			캐시 만료를 지정할 때 : max-age(초단위로 가져온다.)
	2) Pragma(Http 1.0): public(공용 캐시로 저장, gateway 뒤에 있는 프록시 서버에 저장),
						  private(자신의 브라우저에 캐시 저장),
						  no-cache(no-store) -> 캐시 저장 안함	/ 명령어의 차이는 브라우저마다 각 명령어를 받아들이는데에 차이가 있다.
	3) Expires : 캐시 만료 시점(date)
	
	<%
		// 캐시를 저장하지 않을 때 사용하는 명령어가 두 개 있는데,
		// 이는 브라우저마다 받아들이는 것이 다르기 때문에 둘 모두 설정해줘야한다.
		// 그런데 두번째에서 set이 아니라 add를 사용하는 것은
		// no-cache만을 인식하는 브라우저의 경우 no-cache의 정보가 사라지고 그 위로 덮어씌워지기 때문이다.
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
</pre>
<!-- application.getContextPath -> request.getContextPath()와 동일 -->
<img src="<%=application.getContextPath() %>/images/spongebob.png"/>

</body>
</html>