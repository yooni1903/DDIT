<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/scopeDesc.jsp</title>
</head>
<body>
<h4>영역(Scope)</h4>
<pre>
	: 서블릿이나 JSP의 인스턴스의 관리 권한을 컨테이너(tomcat)이 가짐
	-> 전역변수를 통한 데이터 공유가 불가능하다.
	웹 어플리케이션 내에서 데이터를 공유할 목적으로 운영되는 저장 공간 (메모리에 공간을 만들어 놓고 그 메모리를 공유하는 것)
	영역(scope->Map)을 통해 공유되는 데이터 : 속성(attribute->Entry)
	각 영역에 대한 참조를 소유한 기본 객체와 동일한 사용범위(사용주기)를 갖는다.
	
	
	각 범위는 1>2>3>4 순으로 넓어지는데 1과 2의 범위가 같아질 때가 있다. 이때가 Model1일 때이다.
	
	1. page scope(pageContext가 소유) - 범위 : 현재 JSP 안에서만 사용가능
		: 하나의 페이지로 사용범위가 제한
	2. request(HttpServletRequest가 소유) - 각 클라이언트가 요청을 보낼 때마다 생성된다. (statless - response가 내보내질 때 request 안의 정보도 다 사라진다.)
		: 하나의 요청이 살아있는 한 사용범위가 유지된다.
	3. session(HttpSession이 소유) - 각 클라이언트가 세션을 만들 때마다 생성된다.
		: 한 세션동안 사용 가능한 영역
	4. application(ServletContext가 소유) - 싱글톤 패턴으로, 단 한 개의 객체만 생성된다.
		: 어플리케이션과 생명주기가 동일하다.
</pre>
</body>
</html>