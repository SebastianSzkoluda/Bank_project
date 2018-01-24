package bank_dao;

import java.util.*;
import java.sql.*;
import java.io.*;

import bank_core.Bankier;
import dbc_connection.ConnectionDBC;

public class BankierDAO {
	
	private ConnectionDBC conn;
	
	public BankierDAO() throws Exception {
		conn = new ConnectionDBC();
		
	}
	public List<Bankier> getAllBankiers() throws Exception {
		List<Bankier> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = conn.getMyConn().createStatement();
			myRs = myStmt.executeQuery("select * from bankier");
			
			while(myRs.next()) {
				Bankier tempBankier = convertRowToBankier(myRs);
				list.add(tempBankier);
			}
			return list;
		} 
		finally {
			close(myStmt,myRs);
		}
	}
	public List<Bankier> searchBankiers(String imie) throws Exception {
		List<Bankier> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			imie += "%";
			myStmt = conn.getMyConn().prepareStatement("select * from bankier where imie like ?");
			
			myStmt.setString(1, imie);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Bankier tempBankier = convertRowToBankier(myRs);
				list.add(tempBankier);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	private Bankier convertRowToBankier(ResultSet myRs) throws SQLException{
		int bankier_id = myRs.getInt("bankier_id");
		String haslo = myRs.getString("has³o");
		String imie = myRs.getString("imie");
		String nazwisko = myRs.getString("nazwisko");
		
		Bankier tempBankier = new Bankier(bankier_id,haslo,imie,nazwisko);
		
		return tempBankier;		
	}
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException{
		
		if(myRs != null) {
			myRs.close();
		}
		if(myStmt != null) {
			
		}
		if(myConn != null) {
			myConn.close();
		}
	}
	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}
	public static void main(String[] args) throws Exception {
		
		BankierDAO dao = new BankierDAO();
		System.out.println(dao.searchBankiers("sebastian"));

		System.out.println(dao.getAllBankiers());
	}
}
