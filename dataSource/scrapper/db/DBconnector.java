package com.corona.scrapper.db;

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
		    System.out.println("Driver loaded");
			
			con = DriverManager.getConnection("jdbc:mysql://url:3306/db-name","username" ,"password");
		    System.out.println("database connected");
		    
		    st = con.createStatement();
		}
	    catch (ClassNotFoundException e) {
		// TODO: handle exception
		  System.out.println(e);
	    }
		catch (SQLException e) {
			// TODO: handle exception
			 System.out.println(e);
		}
	}
    public static Connection getConnection()
	{
		return con;
	}
	
	public static Statement getStatement()
	{
		return st;
	}
}
