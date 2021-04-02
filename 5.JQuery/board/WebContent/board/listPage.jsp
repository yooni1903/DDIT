<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
	
	int totalp = (Integer)request.getAttribute("totalp");
	int startp = (Integer)request.getAttribute("startp");
	int endp = (Integer)request.getAttribute("endp");
	
%>

{
	"totalp" : "<%= totalp %>",
	"startp" : "<%= startp %>",
	"endp" : "<%= endp %>",
	"datas" : [

<%
	for(int i = 0; i< list.size(); i++){
		BoardVO vo = list.get(i);
		if(i > 0) out.print(",");
%>
	{
		"num" : "<%= vo.getNum() %>",
		"subject" : "<%= vo.getSubject() %>",
		"writer" : "<%= vo.getWriter() %>",
		"cont" : "<%= vo.getContent().replaceAll("\r", "").replaceAll("\n", "<br>") %>",
		"hit" : "<%= vo.getHit() %>",
		"date" : "<%= vo.getWdate() %>",
		"mail" : "<%= vo.getMail() %>",
		"pass" : "<%= vo.getPassword() %>"
	}
	
<%
	}
%>	
	
	
	
	
	 ]
}