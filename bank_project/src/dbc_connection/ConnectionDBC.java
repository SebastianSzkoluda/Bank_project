package dbc_connection;

import java.util.*;
import java.sql.*;
import java.io.*;

public class ConnectionDBC {
	private Connection myConn;
	public ConnectionDBC() throws Exception{
	Class.forName("com.mysql.jdbc.Driver"); 
	
	Properties props = new Properties();
	props.load(new FileInputStream("bank.properties"));
	
	String user = props.getProperty("user");
	String password = props.getProperty("password");
	String dburl = props.getProperty("dburl");
	
	myConn = DriverManager.getConnection(dburl,user,password);
	
	System.out.println("DB connection succesful to: " + dburl);	
	}
	public Connection getMyConn() {
		return myConn;
	}
	public void setMyConn(Connection myConn) {
		this.myConn = myConn;
	}
	
	
}
