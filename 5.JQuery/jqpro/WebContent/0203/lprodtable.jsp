<%@page import="kr.or.ddit.lprod.LprodVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.ibatis.config.SqlMapClientFactory"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 1. 클라이언트 요청시 전송되는 데이터 받기
	// 2. SqlMapClient 객체 얻기
		SqlMapClient aa = SqlMapClientFactory.getClient();

	// 3. sql문 실행 - select - 
		List<LprodVO> list = aa.queryForList("lprod.selectLprod");
%>
	
	<table border="1">
	<tr class="t1">
		<td>LPROD_ID</td>
		<td>LRPOD_GU</td>
		<td>LPROD_NM</td>
	</tr>

<% 

		for(int i = 0; i < list.size(); i++){
			LprodVO vo = list.get(i);

%>
	<tr>
		<td><%= vo.getLprod_id() %></td>
		<td><%= vo.getLprod_gu() %></td>
		<td><%= vo.getLprod_nm() %></td>
	</tr>

<% } %>

</table>