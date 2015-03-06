package com.ourchem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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
	
    public static void save(String table,Integer id,Map<String,Object> data){
    	String sql = "";
    	 if(id==null){
    		 sql = "insert into " + table + "(id,title,CREATEBY_DEPTNA) values (PK_VALUE_SEQUENCE.NEXTVAL,'"
    				      + data.get("title") + "','" + data.get("dept") + "')";
    	 }else{
    		 sql = "update " + table + " set title = '" + data.get("title") + "',CREATEBY_DEPTNA = '"
    				 + data.get("dept") + "' where id=" + id;
    	 } 
    	 try {
    		 Connection conn = getConnection();
			  PreparedStatement	pre = conn.prepareStatement(sql);
			  pre.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
    	 
    }
    
    public static void delete(String table,Integer id){
    	String sql = "delete from " + table + " where id="+ id;
    	try {
   		 Connection conn = getConnection();
			  PreparedStatement	pre = conn.prepareStatement(sql);
			  pre.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
    }
	 
}
