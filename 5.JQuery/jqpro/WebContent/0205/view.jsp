<%@page import="kr.or.ddit.member.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

	// 서블릿에서 최종결과를 받아서 출력한다.
	List<MemberVO> list = (List<MemberVO>)request.getAttribute("listvalue");

%>
<table>
	<tr>
		<td>아이디</td>
		<td>이름</td>
		<td>전화번호</td>
	</tr>
<%
	for(int i = 0; i < list.size(); i++){
		MemberVO vo = list.get(i);
%>
	<tr>
		<td><%= vo.getMem_id() %></td>
		<td><%= vo.getMem_name() %></td>
		<td><%= vo.getMem_hp() %></td>
	</tr>
<%		
	}
%>	
	
	
	
</table>