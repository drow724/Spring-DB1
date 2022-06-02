package hello.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import lombok.extern.slf4j.Slf4j;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {

	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			log.info("get Connection={}. class={}", connection, connection.getClass());
			return connection;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		
	}
}
