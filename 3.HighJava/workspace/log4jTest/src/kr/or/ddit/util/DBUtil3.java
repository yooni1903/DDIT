package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

// JDBC 드라이버를 룅하고 Connection 객체를 생성하는 메서드로 구성된 Class만들기

// ResourceBundle 객체 이용하기
public class DBUtil3 {
	// Logger 객체 생성
	private static final Logger logger = Logger.getLogger(DBUtil3.class);
	
	
	// ResourceBundle 객체 변수 선언
	private static ResourceBundle bundle;
	
	// static 초기화 블럭
	static{
		// 객체 생성 및 파일 내용 읽어오기
		bundle = ResourceBundle.getBundle("dbinfo");
		logger.info("ResourceBundle객체 생성 - dbinfo.properties 파일 읽기");
		
		try {
		//	Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			logger.trace("DB 드라이버 로딩 성공!!!!");
			
		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패~~~");
			logger.error("드라이버 로딩 실패ㅜㅠ " + e);
			e.printStackTrace();
		}
	}
	
	// DB에 접속하고 접속에 성공하면 Connection 객체를 반환하는 메서드
	public static Connection getConnection(){
		try {
			logger.trace("DB에 접속 중~!!!~!");
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
		/*	return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"KDY11", "java");*/
		} catch (SQLException e) {
//			System.out.println("DB연결 실패~~~~");
			logger.error("DB 연결 실패ㅠㅜ " + e);
			return null;
		}
	}
	
}
