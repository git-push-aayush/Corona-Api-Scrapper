package com.corona.tracker.api.status.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DBconnector {
	static Connection con;
	static Statement st;

	 static
	 {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");

			con = DriverManager.getConnection(
					"jdbc:mysql://url:3306/db_name",
					"usernam",
					"password");
			System.out.println("Database connected");
			st = con.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			;
		} catch (SQLException e) {
			System.out.println(e);
		} // end of try catch
	}

	public static Connection getConnection() {
		return con;
	}

	public static Statement getStatement() {
		return st;
	}
}
