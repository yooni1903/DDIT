package kr.or.ddit.servlet08;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.servlet08.dao.JdbcDescDAO;
import kr.or.ddit.servlet08.service.IJdbcDescService;
import kr.or.ddit.servlet08.service.JdbcDescServiceImpl;

@WebServlet("/10/jdbcDesc.do")
public class jdbcDescServlet extends HttpServlet{
	IJdbcDescService service = new  JdbcDescServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String property_name =req.getParameter("property_name");
		
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("property_name", property_name);
		
		List<Map<String, Object>> dataList = service.retrieveDataBaseProperties(pMap);
		String[] headers = (String[]) pMap.get("headers"); 
		
		req.setAttribute("headers", headers);
		req.setAttribute("dataList", dataList);
		
		String view = "/WEB-INF/views/10/jdbcDesc.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	
	

}
