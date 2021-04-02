<%@page import="kr.or.ddit.lprod.LprodVO"%>
<%@page import="kr.or.ddit.ibatis.config.SqlMapClientFactory"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 1. 클라이언트 요청시 전송되는 데이터 받기
	// 2. SqlMapClient 객체 얻기
		SqlMapClient aa = SqlMapClientFactory.getClient();

	// 3. sql문 실행 - select - 
		List<LprodVO> list = aa.queryForList("lprod.selectLprod");
%>
[
<%	
	// 4. 반복문을 실행하면서 json 생성
		for(int i = 0; i < list.size(); i++){
			LprodVO vo = list.get(i);
			if(i > 0) out.print(",");
			
%>

	{
		"id" : "<%= vo.getLprod_id() %>",
		"gu" : "<%= vo.getLprod_gu() %>",
		"nm" : "<%= vo.getLprod_nm() %>"
	}
	

<%
		}
	
%>
]