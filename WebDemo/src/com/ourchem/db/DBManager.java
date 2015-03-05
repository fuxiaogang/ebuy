package com.ourchem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {

	private static Connection conn = null;
	private final static String url = "jdbc:oracle:thin:@192.168.0.2:1521:ORCL";
	private final static String username = "ourchemdev20";
	private final static String password = "ourchemdev20";

	public static Connection getConnection() {
		try {
			if (conn == null) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, username, password);
			}
		} catch (Exception e) {

		}
		return conn;
	}

	public static ResultSet queryList(String sql) {
		ResultSet result = null;
		PreparedStatement pre = null;
		try {
			Connection conn = getConnection();
			pre = conn.prepareStatement(sql);
			result = pre.executeQuery();
		} catch (Exception e) {

		} finally {
			 
		}
		return result;
	}

	 
}
