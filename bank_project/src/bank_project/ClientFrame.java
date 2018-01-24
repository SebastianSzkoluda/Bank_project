package bank_project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;



import dbc_connection.ConnectionDBC;

public class ClientFrame extends JFrame{

	private JFrame frame;
	public JLabel lblNewLabel_Imie;
	private JLabel lblNewLabel_Nazwisko;
	private JLabel lblNumerNIK;
	private JTextField textField_Odbiorca;
	private JTextField textField_Kwota;
	private JTextField textField_N;
	private JTextField textField_O;
	static String login;
	JSplitPane splitPane = new JSplitPane();
	JList<Wiadomosc> list = new JList<>();
	DefaultListModel<Wiadomosc> model = new DefaultListModel<>();
	JLabel label = new JLabel();
	JPanel panel = new JPanel();
	DateFormat dateFormat;
	Date date;
	
	/**
	 * Launch the application.
	 */

	
	public static void clientFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame(login);
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
	
	public ClientFrame(String login) {
		this.login = login;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private class Wiadomosc {
		private String title;
		private String opis;
		
		public Wiadomosc(String t, String o) {
			this.setTitle(t);
			this.setOpis(o);
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getOpis() {
			return opis;
		}

		public void setOpis(String opis) {
			this.opis = opis;
		}
		
		@Override
		public String toString() {
			return title;
		}
		
	}
	
	private void initialize() {
		frame = new JFrame("Okno klienta");
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNumerNIK = new JLabel("Tw\u00F3j numer NIK : ");
		lblNumerNIK.setBounds(10, 41, 133, 14);
		frame.getContentPane().add(lblNumerNIK);
		lblNumerNIK.setVisible(false);
		
		JLabel lblNewLabel_numerNIK2 = new JLabel("Numer rachunku odbiorcy:");
		lblNewLabel_numerNIK2.setBounds(10, 69, 150, 14);
		frame.getContentPane().add(lblNewLabel_numerNIK2);
		lblNewLabel_numerNIK2.setVisible(false);
		
		JLabel lblKwota = new JLabel("Kwota:");
		lblKwota.setBounds(10, 100, 80, 14);
		frame.getContentPane().add(lblKwota);
		lblKwota.setVisible(false);
		
		textField_Odbiorca = new JTextField();
		textField_Odbiorca.setBounds(175, 66, 127, 20);
		frame.getContentPane().add(textField_Odbiorca);
		textField_Odbiorca.setColumns(10);
		textField_Odbiorca.setVisible(false);
		
		textField_Kwota = new JTextField();
		textField_Kwota.setBounds(175, 97, 127, 20);
		frame.getContentPane().add(textField_Kwota);
		textField_Kwota.setColumns(10);
		textField_Kwota.setVisible(false);
		
		JLabel lblNewLabel_Date = new JLabel("Data:");
		lblNewLabel_Date.setBounds(10, 129, 46, 14);
		frame.getContentPane().add(lblNewLabel_Date);
		lblNewLabel_Date.setVisible(false);
		
		JLabel lblNewLabel_Data = new JLabel("");
		lblNewLabel_Data.setBounds(175, 128, 127, 14);
		frame.getContentPane().add(lblNewLabel_Data);
		lblNewLabel_Data.setVisible(false);
		
		JLabel lblWiadomosci = new JLabel("Wiadomoœci");
		lblWiadomosci.setBounds(50, 10, 100, 20);
		frame.getContentPane().add(lblWiadomosci);
		lblNewLabel_Data.setVisible(true);
		
		
		JButton btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.setVisible(true);
		btnWyloguj.setBounds(324, 220, 100, 30);
		
		JButton btnWroc = new JButton("Wróæ");
		btnWroc.setVisible(false);
		btnWroc.setBounds(324, 220, 100, 30);
		
		JButton btnWykonaj = new JButton("Wykonaj");
		btnWykonaj.setVisible(false);
		btnWykonaj.setBounds(185, 196, 100, 30);
		
		JButton btnPrzelew = new JButton("Przelew");
		btnPrzelew.setVisible(true);
		btnPrzelew.setBounds(324, 69, 100, 30);
		
		JButton btnInfo = new JButton("Informacje o koncie");
		btnInfo.setVisible(true);
		btnInfo.setBounds(274, 10, 150, 30);
		
		list.setModel(model);
		model.addElement(new Wiadomosc("Witaj w naszym banku!","Pragniemy powitaæ ciê w naszym banku, liczymy na owocn¹ wspó³pracê!"));
		model.addElement(new Wiadomosc("Promocja!","Jako, ¿e jesteœ naszym nowym klientem, chcemy zaoferowaæ ci kredyt na bardzo intratnych warunkach."));
		
		list.getSelectionModel().addListSelectionListener(e->{
			Wiadomosc w = list.getSelectedValue();
			label.setText("Treœæ : " + w.getOpis());
		});
		
		splitPane.setLeftComponent(new JScrollPane(list));
		panel.add(label);
		splitPane.setRightComponent(new JScrollPane(panel));
		splitPane.setBounds(20, 45, 282, 213);
		
		frame.getContentPane().add(splitPane);
		frame.getContentPane().add(btnWroc);
		frame.getContentPane().add(btnInfo);
		frame.getContentPane().add(btnWyloguj);
		frame.getContentPane().add(btnWykonaj);
		frame.getContentPane().add(btnPrzelew);
		
		JButton btnWprowad = new JButton("Pokaz");
		btnWprowad.setBounds(305, 37, 100, 23);
		frame.getContentPane().add(btnWprowad);
		btnWprowad.setVisible(false);
		
		JLabel lblNumerRachunku = new JLabel("Numer Rachunku : ");
		lblNumerRachunku.setBounds(10, 69, 250, 14);
		frame.getContentPane().add(lblNumerRachunku);
		lblNumerRachunku.setVisible(false);
		
		JLabel lblNazwaRachunku = new JLabel("Nazwa Rachunku : ");
		lblNazwaRachunku.setBounds(10, 100, 250, 14);
		frame.getContentPane().add(lblNazwaRachunku);
		lblNazwaRachunku.setVisible(false);
		
		JLabel lblTyp = new JLabel("Typ : ");
		lblTyp.setBounds(10, 129, 250, 14);
		frame.getContentPane().add(lblTyp);
		lblTyp.setVisible(false);
		
		JLabel lblSaldo = new JLabel("Saldo konta : ");
		lblSaldo.setBounds(10, 156, 250, 14);
		frame.getContentPane().add(lblSaldo);
		lblSaldo.setVisible(false);
		
		JLabel lblnumRach = new JLabel("");
		lblnumRach.setBounds(150, 69, 280, 14);
		frame.getContentPane().add(lblnumRach);
		lblnumRach.setVisible(false);
		
		JLabel lblNazRach = new JLabel("");
		lblNazRach.setBounds(150, 100, 280, 14);
		frame.getContentPane().add(lblNazRach);
		lblNazRach.setVisible(false);
		
		JLabel lblType = new JLabel("");
		lblType.setBounds(150, 129, 280, 14);
		frame.getContentPane().add(lblType);
		lblType.setVisible(false);
		
		JLabel lblSaldoKlienta = new JLabel("");
		lblSaldoKlienta.setBounds(150, 156, 280, 14);
		frame.getContentPane().add(lblSaldoKlienta);
		lblSaldoKlienta.setVisible(false);
		
		JLabel lblNIK = new JLabel("");
		lblNIK.setBounds(150, 41, 80, 14);
		frame.getContentPane().add(lblNIK);
		lblNIK.setVisible(false);
		
		
		btnWprowad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNIK.setText(login);
				String numerNIK1;
				String numerRachunku;
				String nazwaRachunku;
				String typ;
				float saldo = 0;
				numerNIK1 = login;
				String s1SQL = "SELECT * from Konto where k_numerNIK = " + numerNIK1;
				
				try {
					ConnectionDBC con = new ConnectionDBC();
					PreparedStatement myStmt = con.getMyConn().prepareStatement(s1SQL);
					ResultSet rs = myStmt.executeQuery(s1SQL );
					
					while (rs.next()) {
						numerRachunku = rs.getString("numerRachunku");
						nazwaRachunku = rs.getString("nazwaRachunku");
						typ = rs.getString("typ");
						saldo = rs.getFloat("saldo");
						
						lblnumRach.setText(numerRachunku);
						lblNazRach.setText(nazwaRachunku);
						lblType.setText(typ);
						lblSaldoKlienta.setText(Float.toString(saldo));
						
					}
					
					
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Podaj numer NIK!");
					ex.printStackTrace();
				}
					
			}
		});
		
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnWyloguj.setVisible(false);
				btnPrzelew.setVisible(false);
				btnInfo.setVisible(false);
				btnWroc.setVisible(true);
				lblNumerNIK.setVisible(true);
				lblWiadomosci.setVisible(false);
				splitPane.setVisible(false);
				lblNumerRachunku.setVisible(true);
				lblNazwaRachunku.setVisible(true);
				lblTyp.setVisible(true);
				lblSaldo.setVisible(true);
				lblnumRach.setVisible(true);
				lblNazRach.setVisible(true);
				lblType.setVisible(true);
				lblSaldoKlienta.setVisible(true);
				btnWprowad.setVisible(true);
				lblNIK.setVisible(true);
			}
		});
	
		
		
		btnWroc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnPrzelew.setVisible(true);
				btnWyloguj.setVisible(true);
				btnInfo.setVisible(true);
				btnWroc.setVisible(false);
				btnWykonaj.setVisible(false);
				lblNewLabel_numerNIK2.setVisible(false);
				lblKwota.setVisible(false);
				textField_Odbiorca.setVisible(false);
				textField_Kwota.setVisible(false);
				lblNewLabel_Date.setVisible(false);
				lblNewLabel_Data.setVisible(false);
				lblNumerNIK.setVisible(false);
				lblWiadomosci.setVisible(true);
				splitPane.setVisible(true);
				lblNumerRachunku.setVisible(false);
				lblNazwaRachunku.setVisible(false);
				lblTyp.setVisible(false);
				lblSaldo.setVisible(false);
				lblnumRach.setVisible(false);
				lblNazRach.setVisible(false);
				lblType.setVisible(false);
				lblSaldoKlienta.setVisible(false);
				btnWprowad.setVisible(false);
				lblNIK.setVisible(false);
			}
		});
		
		
		btnPrzelew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnWyloguj.setVisible(false);
				btnPrzelew.setVisible(false);
				btnInfo.setVisible(false);
				btnWroc.setVisible(true);
				btnWykonaj.setVisible(true);
				lblNewLabel_numerNIK2.setVisible(true);
				lblKwota.setVisible(true);
				textField_Odbiorca.setVisible(true);
				textField_Kwota.setVisible(true);
				lblNewLabel_Date.setVisible(true);
				lblNewLabel_Data.setVisible(true);
				lblWiadomosci.setVisible(false);
				splitPane.setVisible(false);
				lblNumerRachunku.setVisible(false);
				lblNazwaRachunku.setVisible(false);
				lblTyp.setVisible(false);
				lblSaldo.setVisible(false);
				lblnumRach.setVisible(false);
				lblNazRach.setVisible(false);
				lblType.setVisible(false);
				lblSaldoKlienta.setVisible(false);
				btnWprowad.setVisible(false);
				lblNIK.setVisible(false);
				
				dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				date = new Date();
				//System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
				lblNewLabel_Data.setText(dateFormat.format(date));
			}
		});
		
		btnWykonaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String numerNIK11=null;
				String numerNIK2 = null;
				String numerOdbiorcy=null;
				String k=null;
				Float kwota=null;
				String data = dateFormat.format(date);
				Float newSaldoN=null;
				Float newSaldoO=null;
				String numerRachunkuN = null;
				Float saldoN =null;
				String numerRachunkuO = null;
				Float saldoO =null;
				
				try {
					numerNIK11 = login;
					numerOdbiorcy = textField_Odbiorca.getText();
					k = textField_Kwota.getText();
					kwota = Float.parseFloat(k);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Nie uda³o siê wykonaæ przelewu!","B³¹d",JOptionPane.ERROR_MESSAGE);
				}
				
				//System.out.println(data);
				String s1SQL = "SELECT * from Konto where k_numerNIK = " + numerNIK11;
				String s2SQL = "SELECT * from Konto where numerRachunku = " + numerOdbiorcy;
				String s3SQL = "UPDATE Konto SET saldo = ? WHERE numerRachunku = ? ";
				String s4SQL = "UPDATE Konto SET saldo = ? WHERE numerRachunku = ? ";
				try {
					ConnectionDBC con = new ConnectionDBC();
					System.out.println("Bylem tu!!");
					PreparedStatement myStmt = con.getMyConn().prepareStatement(s1SQL);
					//myStmt.setString(1, numerNadawcy);
					ResultSet rs = myStmt.executeQuery(s1SQL );
					PreparedStatement myStmt1 = con.getMyConn().prepareStatement(s1SQL);
					//myStmt1.setString(1, numerOdbiorcy);
					ResultSet rs1 = myStmt1.executeQuery(s2SQL );
					
					while (rs.next()) {
						numerRachunkuN = rs.getString("numerRachunku");
						saldoN = rs.getFloat("saldo");
						
						
					}
					while (rs1.next()) {
						numerRachunkuO = rs1.getString("numerRachunku");
						saldoO = rs1.getFloat("saldo");
						numerNIK2 = rs1.getString("k_numerNIK");
					}
					
						String s5SQL = "Insert into `bank`.`przelewy` (przelew_id,numerNIK1,numerNIK2,kwota,data,zatwierdzone,f_bankier_id,f_numerRachunku,f_kierownik_id)" + " values (?,?,?,?,?,?,?,?,?)";
						PreparedStatement ps = con.getMyConn().prepareStatement(s5SQL,Statement.RETURN_GENERATED_KEYS);
						 Random rand = new Random();
						 ps.setInt(1, rand.nextInt(100)*10);
						 ps.setString(2, numerNIK11);
						 ps.setString(3, numerNIK2);
						 ps.setFloat(4, kwota);
						 ps.setString(5, data);
						// ps.setInt(6, 1);
						 ps.setBoolean(6, true);
						 ps.setInt(7, 1);
						 ps.setString(8, numerRachunkuN);
						 ps.setInt(9, 4);
						 
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
						ps.getGeneratedKeys();
						
						JOptionPane.showMessageDialog(null, "Transakcja zosta³a dokonana\n Kwota zosta³a przelana na rachunek o podanym numerze" );
					
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
		
		
	}
}
