package com.cyb.phone.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBEntity {
	public static String driverClassName = "org.h2.Driver";
	public static String url = "jdbc:h2:tcp://localhost/~/data/trade;AUTO_SERVER=true";
	public static String username = "sa";
	public static String password = "111111";
	public static Connection getConnection(){
		 try {
			   Class.forName(driverClassName);
			   Connection conn = DriverManager.getConnection(url, username, password);
			   return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] a) throws Exception {
	   Connection conn = getConnection();;
	   Statement stmt = conn.createStatement();
	   ResultSet rs = stmt.executeQuery("SELECT * FROM TEST ");   
       while(rs.next()) {   
        System.out.println(rs.getInt("ID")+","+rs.getString("NAME"));
       }
	   conn.close();
	 }
}
