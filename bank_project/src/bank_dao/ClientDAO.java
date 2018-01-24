package bank_dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bank_core.Client;
import dbc_connection.ConnectionDBC;

public class ClientDAO {
	private ConnectionDBC conn;
	
	public ClientDAO() throws Exception {
		conn = new ConnectionDBC();
		
	}
	public List<Client> getAllClients() throws Exception {
		List<Client> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = conn.getMyConn().createStatement();
			myRs = myStmt.executeQuery("select * from klient");
			
			while(myRs.next()) {
				Client tempClient = convertRowToClient(myRs);
				list.add(tempClient);
			}
			return list;
		} 
		finally {
			close(myStmt,myRs);
		}
	}
	public List<Client> searchClients(String imie) throws Exception {
		List<Client> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			imie += "%";
			myStmt = conn.getMyConn().prepareStatement("select * from klient where imie like ?");
			
			myStmt.setString(1, imie);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Client tempClient = convertRowToClient(myRs);
				list.add(tempClient);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	private Client convertRowToClient(ResultSet myRs) throws SQLException{
		int numerNIK = myRs.getInt("numerNIK");
		String haslo = myRs.getString("haslo");
		String imie = myRs.getString("imie");
		String nazwisko = myRs.getString("nazwisko");
		int wiek = myRs.getInt("wiek");
		BigDecimal pesel = myRs.getBigDecimal("pesel");
		
		Client tempClient = new Client(numerNIK,haslo,imie,nazwisko,wiek,pesel);
		
		return tempClient;		
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
		
		ClientDAO dao = new ClientDAO();
		System.out.println(dao.searchClients("Mateusz"));

		System.out.println(dao.getAllClients());
	}
}
