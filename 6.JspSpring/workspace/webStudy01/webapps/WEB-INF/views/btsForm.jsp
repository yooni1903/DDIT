<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" >
	<select name="member">
	<%
		Map<String, String> btsMap = (Map)application.getAttribute("btsMap");
	
		for(Entry<String, String> entry : btsMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			%>
			<option value="<%=key %>"><%=value %></option>
			<%
		}
	%>
	</select>
	<input type="submit" value="선택"/>
</form>
</body>
</html>