<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	th,td{
		border: 1px solid black;
		
	}
	table{
		border-collapse: collapse;
	}
	.red{
		background: red;
	}
	.green{
		background: lightgreen;
	}
	
	
	/* tr:nth-child(3){
	 background: red;
	}
	td:nth-child(5){background: lightgreen;} */
</style>
</head>
<body>
<%! 	String gugudanText(int i,int j,String clz){
			
			return String.format("<td class='%s'>%d*%d=%d</td>", clz, i, j, (i*j));
		}
	%>
<!-- 2단부터 9단까지의 구구단을 table태그를 이용하여 출력. -->
<!-- 하나의 row에 하나의 단이 출력되도록 -->
<%
	// 문자열을 일정한 형태로 출력하고 싶을 땐 formatting한다라고 하고,
	// 특정 데이터 타입으로 변환하고 싶을 때는 parsing한다고 한다.
	
	String minDanStr = request.getParameter("minDan");
	String maxDanStr = request.getParameter("maxDan");
	
	// parameter가 넘어오지 않았을 경우를 생각해야한다.
	// 기본으로 최소단과 최대단을 설정해놓는다.
	int minDan = 2;
	int maxDan = 9;
	
	if(minDanStr != null && minDanStr.matches("[2-9]")){
		minDan = Integer.parseInt(minDanStr);
	}
	if(maxDanStr != null && maxDanStr.matches("[2-9]")){
		maxDan = Integer.parseInt(maxDanStr);
	}
%>
<form>
	<input type="number" placeholder="최소 단" name="minDan" min="2" max="9" value="<%=minDan%>"/>
	<select name="maxDan" required>
		<option value>최대단</option>
		<%
			String OPTPTRN = "<option %2$s value='%1$d'>%1$d단</option>";
			StringBuffer options = new StringBuffer();
			for(int dan = 2; dan <= 9; dan++){
				
				options.append(
						String.format(OPTPTRN, dan, dan==maxDan?"selected" : "")
						);
			}
			out.println(options);
		%>
		
	</select>	
	<input type="submit" value="전송">
</form>
<table>
	<% 
		int rowcnt = 1;
		for(int i=minDan; i <= maxDan; i ++ ){ 
			String clz = "normal";
			if(rowcnt++ == 3){
				clz = "red";
			}
			out.println(String.format("<tr class='%s'>", clz));
			out.println("<td>" + i + "단</td>");
			for(int j = 1; j <= 9; j ++){ 
				if(j == 4) clz = "green";
				else clz = "normal";
				out.println(
						gugudanText(i, j, clz)
						);
			} 
			out.println("</tr>");
	} %>
	
	
</table>

</body>
</html>