package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/bts", loadOnStartup=2)
public class BTSServlet_Sem extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Map<String, List<String>> btsMap_sem = new LinkedHashMap<>();
		btsMap_sem.put("B001", Arrays.asList("뷔", "/WEB-INF/views/bts/bui.jsp"));
		btsMap_sem.put("B002", Arrays.asList("제이홉", "/WEB-INF/views/bts/jhop.jsp"));
		btsMap_sem.put("B003", Arrays.asList("지민", "/WEB-INF/views/bts/jimin.jsp"));
		btsMap_sem.put("B004", Arrays.asList("진", "/WEB-INF/views/bts/jin.jsp"));
		btsMap_sem.put("B005", Arrays.asList("정국", "/WEB-INF/views/bts/jungkuk.jsp"));
		btsMap_sem.put("B006", Arrays.asList("RM", "/WEB-INF/views/bts/rm.jsp"));
		btsMap_sem.put("B007", Arrays.asList("슈가", "/WEB-INF/views/bts/suga.jsp"));
		getServletContext().setAttribute("btsMap_sem", btsMap_sem);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String view = "/WEB-INF/views/btsForm_sem.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String member = req.getParameter("member");
		
		if(member == null && member.isEmpty()) {
			resp.sendError(400);
			return;
		}
		Map<String, List<String>> btsMap_sem = (Map) getServletContext().getAttribute("btsMap_sem");
		List<String> data = btsMap_sem.get(member);
		
		if(data== null) {
			resp.sendError(404);
			return;
		}
		
		String view = data.get(1);
		req.getRequestDispatcher(view).forward(req, resp);
		
		
		
	}


}
