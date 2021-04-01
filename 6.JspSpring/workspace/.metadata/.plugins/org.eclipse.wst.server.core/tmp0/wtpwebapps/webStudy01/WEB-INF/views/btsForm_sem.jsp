<%@page import="java.util.List"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%--
	//preScript에 있는 정보들을 싸그리싹삭 가져올 예정~!
	pageContext.include("/includee/preScript.jsp");
--%>
<!-- 위의 동적 내포와 같은 역할 -->
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<%-- 
<script type="text/javascript">
	$(function(){
		$("#member").on("change", function(){
			$("#bts").empty();
			
			var value = $(this).val();
			
			$.ajax({
				url : "bts",
				method : "post",
				data : {"member" : value},
				dataType : "html",
				success : function(resp) {
					$('#bts').html(resp);
				},
				error : function(xhr, error, msg) {
					console.log(xhr);
					console.log(error);
					console.log(msg);
				}

			});
			
		});
	})

</script>
--%>
</head>
<body>
<form method="post" >
	<input type="text" name="test" />
	<%
		Map<String, List<String>> btsMap = (Map) application.getAttribute("btsMap_sem");
	%>
	<select name="member" onchange="$(this.form).trigger('submit');">
<!-- 	<select name="member" onchange="this.form.submit();"> -->
		<%
			for(Entry<String, List<String>> entry : btsMap.entrySet()){
				String id = entry.getKey();
				String name = entry.getValue().get(0);
				%>
				<option value="<%=id%>"><%=name %></option>
				<%
			}
		%>
	
	</select>
</form>
<div id="resultArea"></div>
<script type="text/javascript">
	let resultArea = $("#resultArea");
	$("form").test2().formToAjax({
		dataType:"html",
		success : function(resp){
			resultArea.html(resp);
		}
	})
</script>
</body>
</html>