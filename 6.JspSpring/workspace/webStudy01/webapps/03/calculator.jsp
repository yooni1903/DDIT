<%@page import="kr.or.ddit.enumpkg.MimeType"%>
<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="kr.or.ddit.enumpkg.Operator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		var functionMap = {
				json:function(resp){
					resultArea.text(resp.expression);
				},
				xml:function(resp){
					resultArea.text($(resp).find("expression").text());
				},
				html:function(resp){
					resultArea.html(resp);
				},
				plain:function(resp){
					resultArea.text(resp);
				}
		}
		
		var mimeKind = $("#mimeKind");
		var resultArea = $("#resultArea");
		
		
		var calForm = $("#calForm").on("submit", function(event){
			var dataType = mimeKind.val();
			if(!dataType){
				return true;
			}
			event.preventDefault();
			var action = this.action;		// this는 html, $(this)는 JQeury
			var method = this.method;
			var data = $(this).serialize();		// serialize() -> QeuryString 생성
			var options = {
				url : action,
				method : method,
				data : data,
										// Request_Header - Accept : text/plain[html, javascript], application/json[xml]
										// Response_Header - Content-Type : 
				error : function(xhr, error, msg) {
					console.log(xhr);
					console.log(error);
					console.log(msg);
				}

			}
			
			console.log(data);
			options.dataType = dataType;
			options.success = functionMap[options.dataType];
			
			//ajax는 단순한 요청이며, 화면에 락을 걸 건지 아닌지를(비동기, 동기방식) 설정하기 위한 용도이다.
			$.ajax(options);
			return false;	// submit 버튼의 이벤트를 막는 명령어
		});
		
	});
</script>
</head>
<body>
<!-- 1. left, rigth의 피연산자와 operator 이름의 연산자를 포함한 필수 파라미터 입력. -->
<!-- 2. 연산의 종류, 사칙연산 지원 -->
<!-- 3. 입력 데이터는 실수형. -->
<!-- 4. 파라미터 전송 : /03/calculator의 방향으로 전송(전송 데이터는 비노출 - post). -->
<!-- 5. 연산의 결과 : ex) 3 * 4 = 12와 같은 형태로 제공 -->
<select id="mimeKind">
	<option value="">dataType 선택</option>
	<%
		for(MimeType tmp : MimeType.values()){
		%>
			<option value=<%=tmp.name().toLowerCase() %>><%=tmp.name() %></option>
		<%
		}
	%>
</select>
<form id="calForm" action="<%=request.getContextPath() %>/03/calculator" method="post">
	<input type="number" name="left" step="any"/>
	<%
		for(OperatorType tmp : OperatorType.values()){
			%>
			<label><input type="radio" name="operator" value="<%=tmp.name()%>"/><%=tmp.getSign() %></label>
			<%
		}
	
	%>
	<input type="number" name="right" step="any"/>
	<button type="submit">=</button>
</form>
<div id="resultArea"></div>



<!-- 내가 시도했던 코드 -->
<%-- <form action="<%=request.getContextPath() %>/03/calculator" method="post"> --%>
<!-- 	<input type="number" name="left"> -->
<!-- 	<select name="operator" required> -->
<!-- 		<option value>기호</option> -->
<%-- 		<% --%>
<!-- // 			String OPER = "<option value='%1$s'>%1$s</option>"; -->
<!-- // 			StringBuffer options = new StringBuffer(); -->
<!-- // 			for(Operator op : Operator.values()){ -->
<!-- // 				options.append(String.format(OPER, op.getOpName())); -->
<!-- // 			} -->
<!-- // 			out.println(options); -->
<%-- 		%> --%>
<!-- 	</select> -->
<!-- 	<input type="number" name="right"> -->
<!-- 	<input type="submit" value="전송"> -->
<!-- </form> -->

<%-- <% if(request.getAttribute("cal") != null) { --%>
<!-- // 	out.print("<div>"+request.getAttribute("cal")+"</div>"); -->
<!-- // } -->
<%-- %> --%>

</body>
</html>