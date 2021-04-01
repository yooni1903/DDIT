<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session 로그인 시작페이지</title>
</head>
<body>
<!-- 로그인에 성공하면 세션의 key값은 'userId'이고 세션값으로는 '로그인한 id값'을 저장하기로 한다. -->
<%
	// 세션의 key값인 'userId'에 저장된 데이터를 읽어온다.
	// 이 때 값이 null이면 세션에 저장되지 않은 것이 된다.
	String userId = (String)session.getAttribute("userId");

	if(userId ==null){
%>
	<form action="<%=request.getContextPath()%>/sessionLoginSem.do" method="post">
        <table>
            <tr>
                <td> Id : </td>
                <td><input type="text" name="userId" placeholder="id를 입력하세요"></td>
            </tr>
            <tr>
                <td> PASS : </td>
                <td><input type="password" name="pass" placeholder="PassWord를 입력하세요."></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align:center;"><input type="submit" value="Login"></td>
            </tr>
        </table>
        
    </form>
<%
	}else{
%>
	<h3><%=userId %>님 반갑습니다!</h3>
	<a href="<%=request.getContextPath() %>/sessionLogoutSem.do">로그아웃</a>
<%
	}
%>
</body>
</html>