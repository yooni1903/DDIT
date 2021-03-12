<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet File Upload / Download 연습</title>
</head>
<body>
    <h1>File Upload 연습</h1>
    <hr>
    <!-- 
       파일을 서버로 전송하기 위해서는 form의 method는 post이어야 하고,
       enctype="multipart/form-data"를 추가해야 한다.
    -->
    <form action="<%=request.getContextPath()%>/fileUploadServlet.do" method="post" enctype="multipart/form-data">
        ID : <input type="text" name="memid"><br><br>
        Upload File1 : <input type="file" name="file1" multiple><br><br>	<!-- multiple은 파일을 여러개 선택할 수 있다. -->
        Upload File2 : <input type="file" name="file2"><br><br>
        <input type="submit" value="파일전송">
        
    </form>
    <hr>
    
    <a href="<%=request.getContextPath()%>/uploadFilesServlet.do">업로드된 전체 파일 목록 보기</a>
</body>
</html>