<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/implicitObject.jsp</title>
</head>
<body>
<h4>기본 객체(내장 객체)</h4>
<pre>
 : jsp 컨테이너에 의해 서블릿 소스가 파싱되는 단계에서 정의되는 지역변수들
1. request(HttpServletRequest)
2. response(HttpServletResponse)
3. out(JspWriter) : 응답 데이터를 기록하거나 버퍼 제어에 사용.
4. session(HttpSession) : 하나의 세션에 대한 정보를 가진 객체
5. application(ServletContext) : 서버에 대한 정보와 어플리케이션에 대한 정보를 가진 객체
6. config(ServletConfig) - 하나의 서블릿에 대한 메타 정보
7. page(Object) - 하나의 세션에 대한 정보가 뛰어
8. exception(Throwable): 에러 처리 목적의 페이지에서만 사용(isErrorPage="true") 발생한 에러나, 예외에 대한 정보를 가진 객체
****9. pageContext(PageContext) : 가장 먼저 생성되는 기본 객체이면서, 나머지 기본 객체에 대한 참조를 소유함
</pre>

</body>
</html>