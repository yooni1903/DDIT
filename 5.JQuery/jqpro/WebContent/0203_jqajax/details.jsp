<%@page import="kr.or.ddit.buyer.BuyerVO"%>
<%@page import="kr.or.ddit.ibatis.config.SqlMapClientFactory"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//1. 클라이언트 요청시 전송되는 데이터 받기
	String buyer_id = request.getParameter("id");		/* ajax에서 id를 가져와서 buyer_id에 넣는다. */
	// 2. SqlMapClient 객체 얻기
	SqlMapClient client = SqlMapClientFactory.getClient();
	
	//3. sql문 실행 - select - 
	BuyerVO vo = (BuyerVO) client.queryForObject("buyer.selectByDetails", buyer_id);
%>

	<table class ='tab'>
		<tr><td>BUYER_ID</td>
		<td><%= vo.getBuyer_id() %></td>
		
		<tr><td>BUYER_NAME</td>
		<td><%= vo.getBuyer_name() %></td>
		
		<tr><td>BUYER_LGU</td>
		<td><%= vo.getBuyer_lgu() %></td>
		
		<tr><td>BUYER_COMTEL</td>
		<td><%= vo.getBuyer_comtel() %></td>
		
		<tr><td>BUYER_BANK</td>
		<td><%= vo.getBuyer_bank() %></td>
		
		<tr><td>BUYER_BANKNO</td>
		<td><%= vo.getBuyer_bankno() %></td>
		
		<tr><td>BUYER_BANKNAME</td>
		<td><%= vo.getBuyer_bankname() %></td>
		
		<tr><td>BUYER_MAIL</td>
		<td><%= vo.getBuyer_mail() %></td>
		
		<tr><td>BUYER_ZIP</td>
		<td><%= vo.getBuyer_zip() %></td>
		
		<tr><td>BUYER_ADD1</td>
		<td><%= vo.getBuyer_add1() %></td>
		
		<tr><td>BUYER_ADD2</td>
		<td><%= vo.getBuyer_add2() %></td>
	</table>
					