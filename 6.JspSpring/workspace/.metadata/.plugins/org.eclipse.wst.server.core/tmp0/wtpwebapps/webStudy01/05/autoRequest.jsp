<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- <meta http-equiv="Refresh" content="10;url=http://www.naver.com">	바디에 있는 데이터이지만 응답 헤더의 역할을 대신함 -->
<title>05/autoRequest.jsp</title>
</head>
<body>
<h4>Auto Request header</h4>
1) Refresh 헤더를 이용한 방식
<%--
	response.setIntHeader("Refresh", 1);		// 최소단위가 1초라는 것을 설정해줌
--%>

2) html의 meta 태그를 이용
3) JS의 스케쥴링 함수 이용 : setInterval(페이지가 유지되야 유효하는 함수), setTimeout

<br>
<h4>현재 서버의 시각 : <span id="serverTimer"></span></h4>
<h4>현재 클라이언트의 시각 : <span id="clientTimer"></span></h4>
<h4><span id="countdown"></span>초 뒤에 네이버로 이동합니다.</h4>

<!-- 매 1초마다 비동기 요청을 발생시키고, -->
<!-- 클라이언트에게 사용 언어를 입력 받을 것. -->
<!-- 클라이언트에게 가져오고 싶은 시간대를 입력받을 것  -->
<select name="loc">
		<option value="">언어</option>
		<%
		Locale[] locales = Locale.getAvailableLocales();
		for(Locale tmp : locales){
			String dL = tmp.getDisplayLanguage(tmp);
			String dc = tmp.getDisplayCountry(tmp);
			if(dL.isEmpty() && dc.isEmpty()) continue;
			%>
			
			<option value="<%=tmp.toLanguageTag()%>"><%=String.format("%s[%s]",dL,dc) %></option>
			
			<%
		}
		%>
</select>
<select name="timeZone">
	<%
		for(String tmpId : TimeZone.getAvailableIDs()){
			TimeZone tmpZone = TimeZone.getTimeZone(tmpId);
			%>
			<option value="<%=tmpId%>"><%=tmpZone.getDisplayName() %> </option>
			<%
			
		}
	%>
</select>


<script>
	var span = document.querySelector("#countdown");
	var serverTimer = $("#serverTimer");
	var locSel = $("[name='loc']");
	var tzSel = $("[name='timeZone']");
	var clientTimer = $("#clientTimer");
	const INITTIME = 10;
	var timer = INITTIME;	
	var jobId = setInterval(function(){
					span.innerHTML = --timer;
					if(timer == 0) clearInterval(jobId);
				}, 1000);
	
	setInterval(function(){
		var data = {
// 				loc:locSel.val(),
				zone:tzSel.val()
		};
		if(locSel.val()){
			data.loc=locSel.val();
		}else{
			data.loc="<%=request.getLocale().toLanguageTag() %>";
		}
// 		new Intl.DateTimeFoarmat(new Intl.Locale(data.loc));
		var df = new Intl.DateTimeFormat(
				new Intl.Locale(data["loc"]), {//== data.loc
					dateStyle : "long",
					timeStyle : "long"
				}
			); 
		clientTimer.text(df.format(new Date()));
		
		$.ajax({
			url : "<%=request.getContextPath()%>/04/serverTime",
			data: data,
			dataType : "text",
			success : function(resp) {
				serverTimer.text(resp);
			},
			error : function(xhr, error, msg) {
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}

		});
	}, 1000);
// 	setTimeout(function() => {	//	function에는 해야할 일을 그 뒤에는 시간을 넘긴다.
// 		location.reload();
// 	}, 1000);
</script>






<br><br><br>
클라이언트가 주기적으로 서버에게 요청을 보내는 방식 polling
	-> 단점 : 서버에 과부하가 걸린다. 그래서 생긴 방식이 webSocket이고 웹소켓의 단점을 개선하기 위한 방식이
				스톰프 방식이다.


</body>
</html>