package kr.or.ddit.servlet03.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonView extends AbstactView{
	
	@Override
	public void mergeModelAndView(Object target, HttpServletResponse resp) 
	throws IOException{
		// Mashalling & Unmashalling을 할 수 있는 객체
		ObjectMapper mapper = new ObjectMapper();
//		String json = mapper.writeValueAsString(target);
		
		
		// 직렬화
		try(
		PrintWriter out = resp.getWriter();
				){
			// Mashalling과 직렬화를 한꺼번에 해주는 메서드
			mapper.writeValue(out, target);
//			out.print(json);
		}
	}
}
