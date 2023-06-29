package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection createConnection() {
		Connection con =  null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-shopping", "root", "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}
}
