package bank_dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bank_core.Konto;
import bank_core.Przelewy;
import dbc_connection.ConnectionDBC;

public class PrzelewyDAO {
private ConnectionDBC conn;
	
	public PrzelewyDAO() throws Exception {
		conn = new ConnectionDBC();
		
	}
	public List<Przelewy> getAllPrzelewy() throws Exception {
		List<Przelewy> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = conn.getMyConn().createStatement();
			myRs = myStmt.executeQuery("select * from przelewy");
			
			while(myRs.next()) {
				Przelewy tempPrzelew = convertRowToPrzelew(myRs);
				list.add(tempPrzelew);
			}
			return list;
		} 
		finally {
			close(myStmt,myRs);
		}
	}
	public List<Przelewy> searchPrzelew(String przelew_id) throws Exception {
		List<Przelewy> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			przelew_id += "%";
			myStmt = conn.getMyConn().prepareStatement("select * from przelewy where przelew_id like ?");
			
			myStmt.setString(1, przelew_id);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Przelewy tempPrzelew = convertRowToPrzelew(myRs);
				list.add(tempPrzelew);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	private Przelewy convertRowToPrzelew(ResultSet myRs) throws SQLException{

		int przelew_id = myRs.getInt("przelew_id");
		int numerNIK1 = myRs.getInt("numerNIK1");
		int numerNIK2 = myRs.getInt("numerNIK2");
		float kwota = myRs.getFloat("kwota");
		Date data = myRs.getDate("data");
		boolean zatwierdzone = myRs.getBoolean("zatwierdzone");
		int f_bankier_id = myRs.getInt("bankier_id");
		String f_numerRachunku = myRs.getString("k_numerRachunku");
		int f_kierownik_id = myRs.getInt("kierownik_id");
		
		
		Przelewy tempPrzelew = new Przelewy(przelew_id,numerNIK1,numerNIK2,kwota,data,zatwierdzone,f_bankier_id,f_numerRachunku,f_kierownik_id);
		
		return tempPrzelew;		
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
		
		PrzelewyDAO dao = new PrzelewyDAO();
		System.out.println(dao.searchPrzelew("1"));

		System.out.println(dao.getAllPrzelewy());
	}
}
