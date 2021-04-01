<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Login 페이지</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/sessionLogin.do" method="post">
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
</body>
</html>