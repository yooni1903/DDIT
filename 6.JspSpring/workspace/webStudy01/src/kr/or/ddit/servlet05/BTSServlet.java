package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(value="/bts", loadOnStartup=1)
public class BTSServlet extends HttpServlet{
	Map<String, String> btsMap;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		btsMap = new LinkedHashMap<>();
		btsMap.put("bui", "뷔");
		btsMap.put("jhop", "제이홉");
		btsMap.put("jimin", "지민");
		btsMap.put("jin", "진");
		btsMap.put("jungkuk", "정국");
		btsMap.put("rm", "RM");
		btsMap.put("suga", "슈가");
		config.getServletContext().setAttribute("btsMap", btsMap);
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/WEB-INF/views/btsForm.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String member = req.getParameter("member");
		int status = validate(member);
		if(status != 200) {
			resp.sendError(status);
			return;
		}
		String view = "/WEB-INF/views/bts/" + member +".jsp";
		req.getRequestDispatcher(view).forward(req, resp);
		
	}



	private int validate(String member) {
		int status = 200;
		if(member == null || member.isEmpty()) {
			status = 400;
		}else {
			if(!btsMap.containsKey(member)) {
				status = 400;
			}
		}
		return status;
	}
}
