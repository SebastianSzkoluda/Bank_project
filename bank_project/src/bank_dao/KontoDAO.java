package bank_dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bank_core.Client;
import bank_core.Konto;
import dbc_connection.ConnectionDBC;

public class KontoDAO {
private ConnectionDBC conn;
	
	public KontoDAO() throws Exception {
		conn = new ConnectionDBC();
		
	}
	public List<Konto> getAllAccounts() throws Exception {
		List<Konto> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = conn.getMyConn().createStatement();
			myRs = myStmt.executeQuery("select * from konto");
			
			while(myRs.next()) {
				Konto tempKonto = convertRowToKonto(myRs);
				list.add(tempKonto);
			}
			return list;
		} 
		finally {
			close(myStmt,myRs);
		}
	}
	public List<Konto> searchAccounts(String numerRachunku) throws Exception {
		List<Konto> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			numerRachunku += "%";
			myStmt = conn.getMyConn().prepareStatement("select * from konto where numerRachunku like ?");
			
			myStmt.setString(1, numerRachunku);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Konto tempKonto = convertRowToKonto(myRs);
				list.add(tempKonto);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	private Konto convertRowToKonto(ResultSet myRs) throws SQLException{

		
		String numerRachunku = myRs.getString("numerRachunku");
		String nazwaRachunku= myRs.getString("nazwaRachunku");
		String typRachunku = myRs.getString("typRachunku");
		float saldo = myRs.getFloat("saldo");
		int k_numerNIK = myRs.getInt("k_numerNIK");
		
		Konto tempKonto = new Konto(numerRachunku,nazwaRachunku,typRachunku,saldo,k_numerNIK);
		
		return tempKonto;		
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
		
		KontoDAO dao = new KontoDAO();
		System.out.println(dao.searchAccounts("08144010398559260737853533"));

		System.out.println(dao.getAllAccounts());
	}
}

