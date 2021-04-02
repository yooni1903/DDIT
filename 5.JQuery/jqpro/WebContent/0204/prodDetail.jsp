<%@page import="kr.or.ddit.ibatis.config.SqlMapClientFactory"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@page import="kr.or.ddit.prod.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 1. 클라이언트 요청시 전송되는 데이터 받기
		String id = request.getParameter("id");
	// 2. SqlMapClient 객체 얻기
		SqlMapClient client = SqlMapClientFactory.getClient();

	// 3. sql문 실행 - select - 
		ProdVO vo = (ProdVO) client.queryForObject("prod.selectByDetails", id);
%>
{
	"id": "<%= vo.getProd_id() %>",
	"name": "<%= vo.getProd_name() %>",
	"lgu": "<%= vo.getProd_lgu() %>",
	"buyer": "<%= vo.getProd_buyer() %>",
	"cost": "<%= vo.getProd_cost() %>",
	"price": "<%= vo.getProd_price() %>",
	"sale": "<%= vo.getProd_sale() %>",
	"outline": "<%= vo.getProd_outline() %>",
	"detail": "<%= vo.getProd_detail() %>"
}