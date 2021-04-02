<%@page import="kr.or.ddit.buyer.BuyerVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.ibatis.config.SqlMapClientFactory"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	
	//1. 클라이언트 요청시 전송되는 데이터 받기
	// 2. SqlMapClient 객체 얻기
	SqlMapClient aa = SqlMapClientFactory.getClient();
	
	//3. sql문 실행 - select - 
	List<BuyerVO> list =  aa.queryForList("buyer.selectByNames");
%>
[
<%
	for(int i = 0; i < list.size(); i++){
		BuyerVO vo = list.get(i);
		if(i > 0) out.print(",");
%>
	{
		"id" : "<%= vo.getBuyer_id() %>",
		"name" : "<%= vo.getBuyer_name() %>"
		
	}

<%
	}
%>

]


