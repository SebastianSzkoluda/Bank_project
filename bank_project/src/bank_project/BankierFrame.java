package bank_project;

import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bank_dao.ClientDAO;
import bank_dao.PrzelewyDAO;
import dbc_connection.ConnectionDBC;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.TextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;

public class BankierFrame {
int i = 5;
	private JFrame frame;
	private String name;
	private String surname;
	private JLabel IMIE = new JLabel("");
	private JLabel NAZWISKO = new JLabel("");
	private JTextField textField_Nadawca;
	private JTextField textField_Odbiorca;
	private JTextField textField_Kwota;
	private JTextField textField_N;
	private JTextField textField_O;
	static String login;
	DateFormat dateFormat;
	Date date;
	private JTable table;
	
	JSplitPane splitPane = new JSplitPane();
	JList<ClientDAO> list = new JList<>();
	DefaultListModel<ClientDAO> model = new DefaultListModel<>();
	JLabel label = new JLabel();
	JPanel panel = new JPanel();
	
	
	/**
	 * @wbp.nonvisual location=-79,109
	 */
	

	/**
	 * Launch the application.
	 */
	public static void bankierFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankierFrame window = new BankierFrame(login);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BankierFrame(String login) {
		this.login = login;
		initialize();
		getDataFromDatabase();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("Okno bankiera");
		frame.setBounds(100, 100, 535, 299);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		IMIE.setBounds(119, 3, 46, 14);
		frame.getContentPane().add(IMIE);
		IMIE.setVisible(true);

		NAZWISKO.setBounds(158, 3, 103, 14);
		frame.getContentPane().add(NAZWISKO);
		NAZWISKO.setVisible(true);
		
		JLabel lblZalogowanyJako = new JLabel("Zalogowany jako : ");
		lblZalogowanyJako.setBounds(10, 3, 111, 14);
		frame.getContentPane().add(lblZalogowanyJako);
		lblZalogowanyJako.setVisible(true);
		
		
		JLabel lblNewLabel_numerNIK1 = new JLabel("Numer NIK nadawcy:");
		lblNewLabel_numerNIK1.setBounds(10, 41, 133, 14);
		frame.getContentPane().add(lblNewLabel_numerNIK1);
		lblNewLabel_numerNIK1.setVisible(false);
		
		JLabel lblNewLabel_numerNIK2 = new JLabel("Numer NIK odbiorcy:");
		lblNewLabel_numerNIK2.setBounds(10, 91, 133, 14);
		frame.getContentPane().add(lblNewLabel_numerNIK2);
		lblNewLabel_numerNIK2.setVisible(false);
		
		JLabel lblKwota = new JLabel("Kwota:");
		lblKwota.setBounds(10, 140, 80, 14);
		frame.getContentPane().add(lblKwota);
		lblKwota.setVisible(false);
		
		textField_Nadawca = new JTextField();
		textField_Nadawca.setBounds(175, 38, 127, 20);
		frame.getContentPane().add(textField_Nadawca);
		textField_Nadawca.setColumns(10);
		textField_Nadawca.setVisible(false);
		
		textField_Odbiorca = new JTextField();
		textField_Odbiorca.setBounds(175, 88, 127, 20);
		frame.getContentPane().add(textField_Odbiorca);
		textField_Odbiorca.setColumns(10);
		textField_Odbiorca.setVisible(false);
		
		textField_Kwota = new JTextField();
		textField_Kwota.setBounds(175, 137, 127, 20);
		frame.getContentPane().add(textField_Kwota);
		textField_Kwota.setColumns(10);
		textField_Kwota.setVisible(false);
		
		JLabel lblNewLabel_Date = new JLabel("Data:");
		lblNewLabel_Date.setBounds(10, 171, 46, 14);
		frame.getContentPane().add(lblNewLabel_Date);
		lblNewLabel_Date.setVisible(false);
		
		JLabel lblNewLabel_Data = new JLabel("");
		lblNewLabel_Data.setBounds(175, 171, 127, 14);
		frame.getContentPane().add(lblNewLabel_Data);
		lblNewLabel_Data.setVisible(false);
		
		 JButton btnWyloguj = new JButton("Wyloguj");
		 btnWyloguj.setBounds(420, 204, 89, 23);
		 frame.getContentPane().add(btnWyloguj);
		 btnWyloguj.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 frame.dispose();
			 }
		 });
		
		JButton btnZakocz = new JButton("Zako\u0144cz");
		 
		 JButton btnZakoñcz = new JButton("Zako\u0144cz");
		 btnZakocz.setVisible(false);
		 btnZakoñcz.setVisible(false);
		
		JButton btnWykonaj = new JButton("Wykonaj");
		btnWykonaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnZakocz.setVisible(false);
				String numerNIK1 = null;
				String numerNIK2 = null;
				String numerNadawcy = null;
				String numerOdbiorcy = null;
				String k = null;
				Float kwota = null;
				String data = dateFormat.format(date);
				Float newSaldoN = null;
				Float newSaldoO = null;
				String numerRachunkuN = null;
				Float saldoN = null;
				String numerRachunkuO = null;
				Float saldoO = null;
				
				try {
					numerNIK1 = textField_Nadawca.getText();
					numerNIK2 = textField_Odbiorca.getText();
					numerNadawcy = textField_N.getText();
					numerOdbiorcy = textField_O.getText();
					k = textField_Kwota.getText();
					kwota = Float.parseFloat(k);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Coœ posz³o nie tak!!!", "B³¹d", JOptionPane.ERROR_MESSAGE);
				}
				
				//System.out.println(data);
				String s1SQL = "SELECT * from Konto where numerRachunku = " + numerNadawcy;
				String s2SQL = "SELECT * from Konto where numerRachunku = " + numerOdbiorcy;
				String s3SQL = "UPDATE Konto SET saldo = ? WHERE numerRachunku = ? ";
				String s4SQL = "UPDATE Konto SET saldo = ? WHERE numerRachunku = ? ";
				try {
					ConnectionDBC con = new ConnectionDBC();
					System.out.println("Bylem tu!!");
					PreparedStatement myStmt = con.getMyConn().prepareStatement(s1SQL);
					ResultSet rs = myStmt.executeQuery(s1SQL );
					PreparedStatement myStmt1 = con.getMyConn().prepareStatement(s1SQL);
					
					ResultSet rs1 = myStmt1.executeQuery(s2SQL );
					
					while (rs.next()) {
						numerRachunkuN = rs.getString("numerRachunku");
						saldoN = rs.getFloat("saldo");
						System.out.println(numerRachunkuN);
						System.out.println(saldoN);
					}
					while (rs1.next()) {
						numerRachunkuO = rs1.getString("numerRachunku");
						saldoO = rs1.getFloat("saldo");
					}
					
						String s5SQL = "Insert into `przelewy` (przelew_id,numerNIK1,numerNIK2,"
								+ "kwota,data,zatwierdzone,f_bankier_id,f_numerRachunku,f_kierownik_id)" 
								+ " values (?,?,?,?,?,?,?,?,?)";
						PreparedStatement ps = con.getMyConn().prepareStatement(s5SQL);
						 Random rand = new Random();
						 ps.setInt(1, rand.nextInt(100)*10);
						 ps.setString(2, numerNIK1);
						 ps.setString(3, numerNIK2);
						 ps.setFloat(4, kwota);
						 ps.setString(5, data);
						// ps.setInt(6, 1);
						 ps.setBoolean(6, true);
						 ps.setInt(7, 1);
						 ps.setString(8, numerNadawcy);
						 ps.setInt(9, 5);
						 
						 ps.execute();
						
						newSaldoN = saldoN - kwota;
						newSaldoO = saldoO + kwota;
						
						PreparedStatement myStmt2 = con.getMyConn().prepareStatement(s3SQL);
						myStmt2.setFloat(1, newSaldoN);
						myStmt2.setString(2, numerRachunkuN);
						myStmt2.executeUpdate();
						
						PreparedStatement myStmt3 = con.getMyConn().prepareStatement(s4SQL);
						myStmt3.setFloat(1, newSaldoO);
						myStmt3.setString(2, numerRachunkuO);
						myStmt3.executeUpdate();						
						if(true) {

							JOptionPane.showMessageDialog(null, "Uda³o siê wykonaæ przelew!");	
							}
						
					
					
					
				} catch (Exception e) {
					System.out.println("Cos nie taak!!");
					e.printStackTrace();
				}
			}
		});
		btnWykonaj.setBounds(200, 204, 89, 23);
		frame.getContentPane().add(btnWykonaj);
		btnWykonaj.setVisible(false);
		
		JLabel lblNumerRachunkuNadawcy = new JLabel("Numer Rachunku nadawcy:");
		lblNumerRachunkuNadawcy.setBounds(10, 66, 155, 14);
		frame.getContentPane().add(lblNumerRachunkuNadawcy);
		lblNumerRachunkuNadawcy.setVisible(false);
		
		textField_N = new JTextField();
		textField_N.setBounds(175, 63, 127, 20);
		frame.getContentPane().add(textField_N);
		textField_N.setColumns(10);
		textField_N.setVisible(false);
		
		JLabel lblNewLabel_RachunekO = new JLabel("Numer Rachunku odbiorcy:");
		lblNewLabel_RachunekO.setBounds(10, 113, 155, 14);
		frame.getContentPane().add(lblNewLabel_RachunekO);
		lblNewLabel_RachunekO.setVisible(false);
		
		textField_O = new JTextField();
		textField_O.setBounds(175, 113, 127, 20);
		frame.getContentPane().add(textField_O);
		textField_O.setColumns(10);
		textField_O.setVisible(false);
		
		  
	
		 
		 
		 btnZakoñcz.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		btnZakocz.setVisible(false);
		 		String numerNIK1 = null;
				
				String numerNadawcy = null;
				
				String k;
				Float kwota = null;
				String data = dateFormat.format(date);
				float newSaldoN;
				
				String numerRachunkuN = null;
				float saldoN = 0;
				
				try {
					numerNIK1 = textField_Nadawca.getText();
					
					numerNadawcy = textField_N.getText();
					
					k = textField_Kwota.getText();
					kwota = Float.parseFloat(k);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Coœ posz³o nie tak!!!", "B³¹d", JOptionPane.ERROR_MESSAGE);
				}
			
				
				//System.out.println(data);
				String s1SQL = "SELECT * from Konto where numerRachunku = " + numerNadawcy;
				
				String s3SQL = "UPDATE Konto SET saldo = ? WHERE numerRachunku = ? ";
				
				try {
					ConnectionDBC con = new ConnectionDBC();
					System.out.println("Bylem tu!!");
					PreparedStatement myStmt = con.getMyConn().prepareStatement(s1SQL);
					//myStmt.setString(1, numerNadawcy);
					ResultSet rs = myStmt.executeQuery(s1SQL );
					
					
					while (rs.next()) {
						numerRachunkuN = rs.getString("numerRachunku");
						saldoN = rs.getFloat("saldo");
						System.out.println(numerRachunkuN);
						System.out.println(saldoN);
					}
					
					
						String s5SQL = "Insert into `wplaty` (numerNIK,kwota,data,zatwierdzone,bankier_id,k_numerrachunku)" + " values (?,?,?,?,?,?)";
						PreparedStatement ps = con.getMyConn().prepareStatement(s5SQL);
						
						 ps.setString(1, numerNIK1);						 
						 ps.setFloat(2, kwota);
						 ps.setString(3, data);
						// ps.setInt(6, 1);
						 ps.setBoolean(4, false);
						 ps.setInt(5, 1);
						 ps.setString(6, numerNadawcy);
						// ps.setInt(7, 4);
						 
						 ps.execute();
						
						newSaldoN = saldoN + kwota;
				
						
						PreparedStatement myStmt2 = con.getMyConn().prepareStatement(s3SQL);
						myStmt2.setFloat(1, newSaldoN);
						myStmt2.setString(2, numerRachunkuN);
						myStmt2.executeUpdate();
						
						if(true) {

							JOptionPane.showMessageDialog(null, "Uda³o siê wykonaæ wp³atê!");	
							}				
					
					
				} catch (Exception e1) {
					System.out.println("Cos nie taak!!");
					e1.printStackTrace();
				}
		 		
		 		
		 		
		 	}
		 });
		 btnZakoñcz.setBounds(197, 204, 89, 23);
		 frame.getContentPane().add(btnZakoñcz);
		 
		 btnZakocz.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		btnZakocz.setVisible(true);
		 		String numerNIK1 = null;
				
				String numerNadawcy = null;
				
				String k = null;
				Float kwota = null;
				String data = dateFormat.format(date);
				Float newSaldoN;
				
				String numerRachunkuN = null;
				Float saldoN = null;
				
				try {
				numerNIK1 = textField_Nadawca.getText();
				
				numerNadawcy = textField_N.getText();
				
				k = textField_Kwota.getText();
				
				    
				    
				
				kwota = Float.parseFloat(k);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Coœ posz³o nie tak!!!", "B³¹d", JOptionPane.ERROR_MESSAGE);
				}
				//System.out.println(data);
				String s1SQL = "SELECT * from Konto where numerRachunku = " + numerNadawcy;
				
				String s3SQL = "UPDATE Konto SET saldo = ? WHERE numerRachunku = ? ";
				
				try {
					ConnectionDBC con = new ConnectionDBC();
					System.out.println("Bylem tu!!");
					PreparedStatement myStmt = con.getMyConn().prepareStatement(s1SQL);
					//myStmt.setString(1, numerNadawcy);
					ResultSet rs = myStmt.executeQuery(s1SQL );
					
					
					while (rs.next()) {
						numerRachunkuN = rs.getString("numerRachunku");
						saldoN = rs.getFloat("saldo");
						System.out.println(numerRachunkuN);
						System.out.println(saldoN);
					}
					
					
						String s5SQL = "Insert into `wyplaty` (numerNIK,kwota,data,zatwierdzone,bankier_id,k_numerrachunku)" + " values (?,?,?,?,?,?)";
						PreparedStatement ps = con.getMyConn().prepareStatement(s5SQL);
						
						 ps.setString(1, numerNIK1);						 
						 ps.setFloat(2, kwota);
						 ps.setString(3, data);
						// ps.setInt(6, 1);
						 ps.setBoolean(4, false);
						 ps.setInt(5, 1);
						 ps.setString(6, numerNadawcy);
						// ps.setInt(7, 4);
						 ps.execute();
					
						newSaldoN = saldoN - kwota;
				
						
						PreparedStatement myStmt2 = con.getMyConn().prepareStatement(s3SQL);
						myStmt2.setFloat(1, newSaldoN);
						myStmt2.setString(2, numerRachunkuN);
						myStmt2.executeUpdate();
						
												
						
						if(true) {

							JOptionPane.showMessageDialog(null, "Uda³o siê wykonaæ wyp³atê!");	
							}
						
						
					
					
					
				} catch (Exception e1) {
					System.out.println("Cos nie taak!!");
					e1.printStackTrace();
				}
		 		
		 		
		 	}
		 });
		 btnZakocz.setBounds(197, 206, 89, 23);
		 frame.getContentPane().add(btnZakocz);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollPane.setBounds(30, 28, 455, 154);
		 scrollPane.setVisible(false);
		 frame.getContentPane().add(scrollPane);
		 
		 table = new JTable();
		 table.setBounds(0, 0, -2, 0);
		 table.setEnabled(true);
		 scrollPane.setViewportView(table);
		 table.setVisible(false);
		 
		
		 btnZakoñcz.setVisible(false);
		
	//------------------------------------------------------------MENU GÓRNY PANEL ------------------------------------------------------------	
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Rejestracja");
		menuBar.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterFrame register = new RegisterFrame();
			       register.registerFrame();				
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("Transakcje");
		menuBar.add(mnNewMenu_1);
		
		
		JMenuItem mntmNewMenuItem_Wplaty = new JMenuItem("Wp\u0142aty");
		mnNewMenu_1.add(mntmNewMenuItem_Wplaty);
		mntmNewMenuItem_Wplaty.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnZakoñcz.setVisible(true);
				btnZakocz.setVisible(false);
				scrollPane.setVisible(false);
				lblNewLabel_numerNIK1.setVisible(true);
				lblNewLabel_numerNIK2.setVisible(false);
				lblKwota.setVisible(true);
				textField_Nadawca.setVisible(true);
				textField_Odbiorca.setVisible(false);
				textField_Kwota.setVisible(true);
				lblNewLabel_Date.setVisible(true);
				lblNewLabel_Data.setVisible(true);
				btnWykonaj.setVisible(true);
				lblNumerRachunkuNadawcy.setVisible(true);
				textField_N.setVisible(true);
				lblNewLabel_RachunekO.setVisible(false);
				textField_O.setVisible(false);
				table.setVisible(false);
				btnWykonaj.setVisible(false);
				
				dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				date = new Date();
				lblNewLabel_Data.setText(dateFormat.format(date));
				
			}
		});
		
		JMenuItem mntmNewMenuItem_Wyplaty = new JMenuItem("Wyp\u0142aty");
		mnNewMenu_1.add(mntmNewMenuItem_Wyplaty);
		mntmNewMenuItem_Wyplaty.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnZakoñcz.setVisible(false);
				btnZakocz.setVisible(true);
				scrollPane.setVisible(false);
				lblNewLabel_numerNIK1.setVisible(true);
				lblNewLabel_numerNIK2.setVisible(false);
				lblKwota.setVisible(true);
				textField_Nadawca.setVisible(true);
				textField_Odbiorca.setVisible(false);
				textField_Kwota.setVisible(true);
				lblNewLabel_Date.setVisible(true);
				lblNewLabel_Data.setVisible(true);
				btnWykonaj.setVisible(true);
				lblNumerRachunkuNadawcy.setVisible(true);
				textField_N.setVisible(true);
				lblNewLabel_RachunekO.setVisible(false);
				textField_O.setVisible(false);
				table.setVisible(false);
				btnWykonaj.setVisible(false);
				
				dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				date = new Date();
				lblNewLabel_Data.setText(dateFormat.format(date));
				
			}
		});
		
		JMenuItem mntmNewMenuItem_Przelewy = new JMenuItem("Przelewy");
		mnNewMenu_1.add(mntmNewMenuItem_Przelewy);
		mntmNewMenuItem_Przelewy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnZakoñcz.setVisible(false);
				btnZakocz.setVisible(false);
				scrollPane.setVisible(false);
				lblNewLabel_numerNIK1.setVisible(true);
				lblNewLabel_numerNIK2.setVisible(true);
				lblKwota.setVisible(true);
				textField_Nadawca.setVisible(true);
				textField_Odbiorca.setVisible(true);
				textField_Kwota.setVisible(true);
				lblNewLabel_Date.setVisible(true);
				lblNewLabel_Data.setVisible(true);
				btnWykonaj.setVisible(true);
				lblNumerRachunkuNadawcy.setVisible(true);
				textField_N.setVisible(true);
				lblNewLabel_RachunekO.setVisible(true);
				textField_O.setVisible(true);
				table.setVisible(false);
				btnWykonaj.setVisible(true);
				
				dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				date = new Date();
				lblNewLabel_Data.setText(dateFormat.format(date));
				
			}
		});
		
		JMenuItem mntmListaKlentw = new JMenuItem("Lista klient\u00F3w");
		menuBar.add(mntmListaKlentw);
		mntmListaKlentw.addActionListener(new ActionListener() {
			
			private boolean isClicked = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				btnZakocz.setVisible(false);
				btnZakoñcz.setVisible(false);
				scrollPane.setVisible(true);
				table.setVisible(true);
				btnWykonaj.setVisible(false);
				lblNewLabel_numerNIK1.setVisible(false);
				lblNewLabel_numerNIK2.setVisible(false);
				lblKwota.setVisible(false);
				textField_Nadawca.setVisible(false);
				textField_Odbiorca.setVisible(false);
				textField_Kwota.setVisible(false);
				lblNewLabel_Date.setVisible(false);
				lblNewLabel_Data.setVisible(false);
				lblNumerRachunkuNadawcy.setVisible(false);
				textField_N.setVisible(false);
				lblNewLabel_RachunekO.setVisible(false);
				textField_O.setVisible(false);
				
				DefaultTableModel model = new DefaultTableModel();
				Object[] columnsName = new Object[6];				
				
			
				columnsName[0] = "numerNIK";
				columnsName[1] = "has³o";
				columnsName[2] = "imiê";
				columnsName[3] = "nazwisko";
				columnsName[4] = "wiek";
				columnsName[5] = "pesel";
				
				
				model.setColumnIdentifiers(columnsName);
				 Object[] rowData = new Object[6];
				
				 
				 try {
					 ClientDAO dao = new ClientDAO();
					for(int i=0;i<dao.getAllClients().size();i++) {
						rowData[0] = dao.getAllClients().get(i).getNumerNIK();
						rowData[1] = dao.getAllClients().get(i).getHaslo();
						rowData[2] = dao.getAllClients().get(i).getImie(); 
						rowData[3] = dao.getAllClients().get(i).getNazwisko(); 
						rowData[4] = dao.getAllClients().get(i).getWiek(); 
						rowData[5] = dao.getAllClients().get(i).getPesel(); 
						
						model.addRow(rowData);
					 }
					table.setModel(model);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(4).setPreferredWidth(50);
					table.getColumnModel().getColumn(5).setPreferredWidth(105);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(!isClicked) {
				  /*JScrollPane scroll = new JScrollPane();
				  scroll.setVisible(true);
				  panel.add(scroll);
				  scroll.setViewportView(table);
				 */
				  isClicked = true;
			}
				 
				
			}
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Historia transakcji");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnZakocz.setVisible(false);
				btnZakoñcz.setVisible(false);
				scrollPane.setVisible(true);
				table.setVisible(true);
				btnWykonaj.setVisible(false);
				lblNewLabel_numerNIK1.setVisible(false);
				lblNewLabel_numerNIK2.setVisible(false);
				lblKwota.setVisible(false);
				textField_Nadawca.setVisible(false);
				textField_Odbiorca.setVisible(false);
				textField_Kwota.setVisible(false);
				lblNewLabel_Date.setVisible(false);
				lblNewLabel_Data.setVisible(false);
				lblNumerRachunkuNadawcy.setVisible(false);
				textField_N.setVisible(false);
				lblNewLabel_RachunekO.setVisible(false);
				textField_O.setVisible(false);
				
				DefaultTableModel model = new DefaultTableModel();
				Object[] columnsName = new Object[5];				
				
			
				columnsName[0] = "ID";
				columnsName[1] = "Numer NIK nadawcy";
				columnsName[2] = "Numer NIK odbiorcy";
				columnsName[3] = "Kwota";
				columnsName[4] = "Data";
				
				
				model.setColumnIdentifiers(columnsName);
				 Object[] rowData = new Object[5];
				
				 
				 try {
					 PrzelewyDAO dao = new PrzelewyDAO();
					for(int i=0;i<dao.getAllPrzelewy().size();i++) {
						rowData[0] = dao.getAllPrzelewy().get(i).getPrzelew_id();
						rowData[1] = dao.getAllPrzelewy().get(i).getNumerNIK1();
						rowData[2] = dao.getAllPrzelewy().get(i).getNumerNIK2();
						rowData[3] = dao.getAllPrzelewy().get(i).getKwota(); 
						rowData[4] = dao.getAllPrzelewy().get(i).getData();
						
						model.addRow(rowData);
					 }
					table.setModel(model);
					//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(50);
					table.getColumnModel().getColumn(1).setPreferredWidth(130);
					table.getColumnModel().getColumn(2).setPreferredWidth(130);
					table.getColumnModel().getColumn(3).setPreferredWidth(50);
					table.getColumnModel().getColumn(4).setPreferredWidth(100);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuBar.add(mntmNewMenuItem_2);
		
	}
	
	private void getDataFromDatabase() {
		
		String id = login;
    	String s1SQL = "SELECT * from bankier where bankier_id = " + id;
    	try {
    		ConnectionDBC con = new ConnectionDBC();
			PreparedStatement myStmt = con.getMyConn().prepareStatement(s1SQL);
			ResultSet rs = myStmt.executeQuery(s1SQL );
			while (rs.next()) {
				name = rs.getString("imie");
				surname = rs.getString("nazwisko");
				IMIE.setText(name);
				NAZWISKO.setText(surname);
				
			}
    	}catch (Exception ex) {
    		ex.printStackTrace();
    	}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
