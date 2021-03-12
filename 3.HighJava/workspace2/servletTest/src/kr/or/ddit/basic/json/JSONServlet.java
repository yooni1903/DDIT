package kr.or.ddit.basic.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/JSONServlet.do")
public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String choice = request.getParameter("choice");
		
		// GSON
		Gson gson = new Gson();
		String jsonData = null;				// JSON으로 변환된 데이터가 저장될 변수 선언
		switch(choice) {
		case "str":
			String str = "안녕하세요";
			jsonData = gson.toJson(str);	// JSON형태의 데이터로 변환한다.
			break;
		case "array":
			int[] nums = {100, 200, 300, 400};
			jsonData = gson.toJson(nums);
			break;
		case "obj":
			SampleVO samvo = new SampleVO(0, "홍길동");
			jsonData = gson.toJson(samvo);
			break;
		case "list":
			ArrayList<SampleVO> list = new ArrayList<>();
			list.add(new SampleVO(100,  "강감찬"));
			list.add(new SampleVO(200,  "이순신"));
			list.add(new SampleVO(300,  "일지매"));
			jsonData = gson.toJson(list);
			break;
		case "map":
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", "성춘향");
			map.put("tel", "010-1234-5688");
			map.put("addr", "대전");
			jsonData = gson.toJson(map);
			break;
		}
		
		System.out.println("jsonData : " + jsonData);
		
		
		// 변환된 JSON데이터를 응답으로 보내준다.
		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
		
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.write(jsonData);
		out.flush();
		out.close();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
