<%@page import="kr.or.ddit.enumpkg.OSType"%>
<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/userAgent.jsp</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%
// String browser = "";
// String userAgent = request.getHeader("User-Agent");
// String chk = "당신의 브라우저는 %s입니다.";

// if(userAgent.contains("Trident") || userAgent.contains("MSIE")){
// 	browser = "익스플로러";
// }else if(userAgent.contains("Edg")){
// 	browser = "엣지";
// }else if(userAgent.contains("Chrome")){
// 	browser = "크롬";
// }else{
// 	browser ="기타 등등";
// }

// 선생님 코드
// Map<String, String> browserMap = new LinkedHashMap<>();
// browserMap.put("EDG", "엣지");
// browserMap.put("CHROME", "크롬");
// browserMap.put("TRIDENT", "익스플로러");
// browserMap.put("OTHER", "기타등등");
String agent = request.getHeader("user-agent");
System.out.println(agent);
String message = null;
String MSGPTRN = null;

if(agent.contains("Mobile")){
	MSGPTRN = "당신의 OS는 %s입니다";
	message = String.format(MSGPTRN, OSType.getOSName(agent));
	
}else{

MSGPTRN = "당신의 브라우저는 %s입니다";
// String name = browserMap.get("OTHER");

// for(Entry<String,String> entry : browserMap.entrySet()){
// 	String keyWord = entry.getKey();
	
// 	if(agent.contains(keyWord)){
// 		name = entry.getValue();
// 		break;
// 	}
// }
message = String.format(MSGPTRN, BrowserType.getBrowserName(agent));
}

// 이렇게 짰을 때 새로운 형태의 브라우저가 생겼을 때 browserMap에 그 정보만 넣어주면 코드를 수정하지 않아도 유지가 된다.


%>

<script type="text/javascript">
<%-- 	alert("<%=String.format(chk, browser)%>"); --%>
<%-- 	$(document).ready(function(){	alert("<%= String.format(chk, browser) %>");}); --%>
 alert("<%=message%>");
</script>
</head>
<body>
<h4>User Agent</h4>
클라이언트의 브라우저를 확인하고, <br>
i explore라면 "당신의 브라우저는 익스플로러입니다."<br>
chrome이라면 "당신의 브라우저는 크롬입니다."<br>
edge라면 "당신의 브라우저는 엣지입니다."<Br>
라는 메세지를 alert창으로 띄울 것<br>



</body>
</html>