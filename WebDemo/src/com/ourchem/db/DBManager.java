package com.ourchem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DBManager {

	private static Connection conn = null;
	
	//数据库url
	private final static String url = "jdbc:oracle:thin:@192.168.0.2:1521:ORCL";
	
	//数据库用户名
	private final static String username = "ourchemdev20";
	
	//数据库密码
	private final static String password = "ourchemdev20";

	/**
	 * 建立数据库的连接
	 * 
	 * @return
	 */
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

	/**
	 * 传入sql查询语句，执行查询，返回结果集 
	 * 
	 * @param sql : eg: select * from table 
	 * @return
	 */
	public static ResultSet queryList(String sql) {
		ResultSet result = null;
		PreparedStatement pre = null;
		try {
			Connection conn = getConnection();  //获取数据库
			pre = conn.prepareStatement(sql);  //预编译的sql语句
			result = pre.executeQuery();  //根据sql，执行查询，返回结果集  
		} catch (Exception e) {

		} finally {
			 
		}
		return result;  //返回结果集  
	}
	
	/**  
	 * 保存数据
	 * 
	 * @param table 数据库表名 
	 * @param id    记录ID
	 * @param data  要保存的数据 
	 */
    public static void save(String table,Integer id,Map<String,Object> data){
    	String sql = "";
    	
    	/**
    	 * id = null ,执行插入数据库 eg : insert into table values(id,a1,a2);
    	 * id != null, 执行更新数据库 eg： update table set a1=1,a2=2 where id = 1
    	 */
    	 if(id==null){
    		 sql = "insert into " + table + "(id,title,CREATEBY_DEPTNA) values (PK_VALUE_SEQUENCE.NEXTVAL,'"
    				      + data.get("title") + "','" + data.get("dept") + "')";
    	 }else{
    		 sql = "update " + table + " set title = '" + data.get("title") + "',CREATEBY_DEPTNA = '"
    				 + data.get("dept") + "' where id=" + id;
    	 } 
    	 try {
    		  Connection conn = getConnection();   //获取连接 
			  PreparedStatement	pre = conn.prepareStatement(sql);  //预编译的sql语句
			  pre.executeUpdate();   //执行更新
		} catch (SQLException e) { 
			e.printStackTrace();
		}
    	 
    }
    
    /**
     * 删除数据
     * @param table 数据库表名 
     * @param id    需要删除的记录ID
     */
    public static void delete(String table,Integer id){
    	String sql = "delete from " + table + " where id="+ id;  //eg ： delete from table where id =1 
    	 
    	try {
   		      Connection conn = getConnection();  //获取连接 
			  PreparedStatement	pre = conn.prepareStatement(sql); //预编译的sql语句
			  pre.executeUpdate();  //执行删除
		} catch (SQLException e) { 
			e.printStackTrace();
		}
    }
	 
}
