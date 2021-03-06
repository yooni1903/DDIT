package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Factory ObjectpMenthod Pattern
 * 	: 객체 생성을 전담하는 객체를 운영하는 구조.
 * 
 *
 */
public class ConnectionFactory {
	private static String driverClassName;
	private static String url;
	private static String user;
	private static String password;
	// static 코드 블럭.  ClassLoader에 의해 어플리케이션이 한 번 구동될 때 최초에 한 번 메모리로 올라간다.
	static{
		// Map과의 차이점. 
		// Map은 인메모리 구조로 이루어져 있어서 어플리케이션이 종료되면 그 정보가 날아간다.
		// 하지만 properties는 임포트 익스포트가 가능한 구조로 정보를 계속 갖고 잇을 수 있다.
		Properties properties = new Properties();
		
		try(InputStream is =  ConnectionFactory.class.getResourceAsStream("dbInfo.properties")) 
		{
			properties.load(is);
			driverClassName = properties.getProperty("driverClassName");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버를 메모리에 로딩시켜줌
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException(e);
		}	
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}

}
