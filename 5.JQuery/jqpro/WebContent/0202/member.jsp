<%@page import="kr.or.ddit.member.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.ibatis.config.SqlMapClientFactory"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClientBuilder"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@page import="java.io.Reader"%>
<%@page import="com.ibatis.common.resources.Resources"%>
<%@page import="java.nio.charset.Charset"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//클라이언트 요청시 전송되는 데이터를 받는다.
	// CRUD처리를 한다.
	// - 1. SqlMapClient객체를 얻어오기 - SqlMapClientFactory.getClient()
	// - 2. sql문을 실행 - mapper파일의 namespace.id이름
	// 		select (결과가 하나일 때) -> aa.queryForObject(namespace.id이름);
	// 		select (결과가 여러 개일 때)-> aa.queryForList(namespace.id이름);
	
	

	
	// 처리 결과로 응답 데이터를 생성한다 - json, text, html, xml
%>

<% 
	// 1. SqlMapclient객체를 얻어오기
	SqlMapClient aa = SqlMapClientFactory.getClient();
	
	// 2. sql문으로 실행
	List<MemberVO> list =  aa.queryForList("member.selectAll");
	
%>
[
<%
	//3. 실행 결과로 json데이터 생성
	for(int i = 0; i <list.size(); i++){
		MemberVO vo = list.get(i);
		if(i > 0) out.print(","); /* out.print => jsp에서 사용하는 printWriter 객체 (java의 System.out.print와 같다) */
		
%>
	{
		"id" : "<%= vo.getMem_id() %>",
		"name" : "<%= vo.getMem_name() %>",
		"hp" : "<%= vo.getMem_hp() %>",
		"mail" : "<%= vo.getMem_mail() %>"
	}


<%
	}
%>
]

