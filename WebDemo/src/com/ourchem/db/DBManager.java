package com.ourchem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DBManager {

	private static Connection conn = null;
	
	//���ݿ�url
	private final static String url = "jdbc:oracle:thin:@192.168.0.2:1521:ORCL";
	
	//���ݿ��û���
	private final static String username = "ourchemdev20";
	
	//���ݿ�����
	private final static String password = "ourchemdev20";

	/**
	 * �������ݿ������
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
	 * ����sql��ѯ��䣬ִ�в�ѯ�����ؽ���� 
	 * 
	 * @param sql : eg: select * from table 
	 * @return
	 */
	public static ResultSet queryList(String sql) {
		ResultSet result = null;
		PreparedStatement pre = null;
		try {
			Connection conn = getConnection();  //��ȡ���ݿ�
			pre = conn.prepareStatement(sql);  //Ԥ�����sql���
			result = pre.executeQuery();  //����sql��ִ�в�ѯ�����ؽ����  
		} catch (Exception e) {

		} finally {
			 
		}
		return result;  //���ؽ����  
	}
	
	/**  
	 * ��������
	 * 
	 * @param table ���ݿ���� 
	 * @param id    ��¼ID
	 * @param data  Ҫ��������� 
	 */
    public static void save(String table,Integer id,Map<String,Object> data){
    	String sql = "";
    	
    	/**
    	 * id = null ,ִ�в������ݿ� eg : insert into table values(id,a1,a2);
    	 * id != null, ִ�и������ݿ� eg�� update table set a1=1,a2=2 where id = 1
    	 */
    	 if(id==null){
    		 sql = "insert into " + table + "(id,title,CREATEBY_DEPTNA) values (PK_VALUE_SEQUENCE.NEXTVAL,'"
    				      + data.get("title") + "','" + data.get("dept") + "')";
    	 }else{
    		 sql = "update " + table + " set title = '" + data.get("title") + "',CREATEBY_DEPTNA = '"
    				 + data.get("dept") + "' where id=" + id;
    	 } 
    	 try {
    		  Connection conn = getConnection();   //��ȡ���� 
			  PreparedStatement	pre = conn.prepareStatement(sql);  //Ԥ�����sql���
			  pre.executeUpdate();   //ִ�и���
		} catch (SQLException e) { 
			e.printStackTrace();
		}
    	 
    }
    
    /**
     * ɾ������
     * @param table ���ݿ���� 
     * @param id    ��Ҫɾ���ļ�¼ID
     */
    public static void delete(String table,Integer id){
    	String sql = "delete from " + table + " where id="+ id;  //eg �� delete from table where id =1 
    	 
    	try {
   		      Connection conn = getConnection();  //��ȡ���� 
			  PreparedStatement	pre = conn.prepareStatement(sql); //Ԥ�����sql���
			  pre.executeUpdate();  //ִ��ɾ��
		} catch (SQLException e) { 
			e.printStackTrace();
		}
    }
	 
}
