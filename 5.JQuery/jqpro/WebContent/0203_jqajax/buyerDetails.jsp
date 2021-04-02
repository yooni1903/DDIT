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

{

	"id" : "<%= vo.getBuyer_id() %>",
	"name" : "<%= vo.getBuyer_name() %>",
	"lgu" : "<%= vo.getBuyer_lgu() %>",
	"comtel" : "<%= vo.getBuyer_comtel() %>",
	"bank" : "<%= vo.getBuyer_bank() %>",
	"bankno" : "<%= vo.getBuyer_bankno() %>",
	"bankname" : "<%= vo.getBuyer_bankname() %>",
	"mail" : "<%= vo.getBuyer_mail() %>",
	"zip" : "<%= vo.getBuyer_zip() %>",
	"add1" : "<%= vo.getBuyer_add1() %>",
	"add2" : "<%= vo.getBuyer_add2() %>"

}
