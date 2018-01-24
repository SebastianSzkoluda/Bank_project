package bank_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import dbc_connection.ConnectionDBC;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RegisterFrame {

	private JFrame frame;
	private JTextField textField_Has³o;
	private JTextField textField_Wiek;
	private JTextField textField_Nazwisko;
	private JTextField textField_Imie;
	private JTextField textField_numerNIK;
	private JTextField textField_Pesel;
	private JTextField textField_NazwaRachunku;
	private JTextField textField_TypRachunku;
	private JTextField textField_Saldo;
	private String nrRachunku;
	/**
	 * Launch the application.
	 */
	public static void registerFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame window = new RegisterFrame();
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
	public RegisterFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Rejestracja");
		frame.setBounds(100, 100, 462, 303);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelnumerRachunku = new JLabel("");
		labelnumerRachunku.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelnumerRachunku.setBounds(156, 11, 165, 14);
		frame.getContentPane().add(labelnumerRachunku);
		labelnumerRachunku.setVisible(false);
		
		JButton btnGeneruj = new JButton("Wygeneruj");
		btnGeneruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[] numerRachunku = new int[26];
				Random rand = new Random();
				for(int i = 0; i < 25; i++) {
					numerRachunku[i] = rand.nextInt(10); 
				}
				nrRachunku = "";
				for(int num: numerRachunku)
					nrRachunku += num;
				System.out.println(nrRachunku);
				
				labelnumerRachunku.setText(nrRachunku);
			}
		});
		btnGeneruj.setBounds(331, 7, 105, 23);
		frame.getContentPane().add(btnGeneruj);
		btnGeneruj.setVisible(false);
		
		JLabel lblNumerRachunku = new JLabel("Numer Rachunku:");
		lblNumerRachunku.setBounds(10, 11, 129, 14);
		frame.getContentPane().add(lblNumerRachunku);
		lblNumerRachunku.setVisible(false);
		
		JLabel lblNazwaRachunku = new JLabel("Nazwa Rachunku:");
		lblNazwaRachunku.setBounds(10, 36, 129, 14);
		frame.getContentPane().add(lblNazwaRachunku);
		lblNazwaRachunku.setVisible(false);
		
		textField_NazwaRachunku = new JTextField();
		textField_NazwaRachunku.setBounds(149, 33, 172, 17);
		frame.getContentPane().add(textField_NazwaRachunku);
		textField_NazwaRachunku.setColumns(10);
		textField_NazwaRachunku.setVisible(false);
		
		JLabel lblTypRachunku = new JLabel("Typ Rachunku:");
		lblTypRachunku.setBounds(10, 61, 129, 14);
		frame.getContentPane().add(lblTypRachunku);
		lblTypRachunku.setVisible(false);
		
		textField_TypRachunku = new JTextField();
		textField_TypRachunku.setBounds(149, 59, 172, 17);
		frame.getContentPane().add(textField_TypRachunku);
		textField_TypRachunku.setColumns(10);
		textField_TypRachunku.setVisible(false);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(10, 86, 96, 14);
		frame.getContentPane().add(lblSaldo);
		lblSaldo.setVisible(false);
		
		textField_Saldo = new JTextField();
		textField_Saldo.setBounds(149, 83, 172, 17);
		frame.getContentPane().add(textField_Saldo);
		textField_Saldo.setColumns(10);
		textField_Saldo.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Imi\u0119:");
		lblNewLabel.setBounds(10, 11, 82, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nazwisko:");
		lblNewLabel_1.setBounds(10, 36, 82, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Wiek:");
		lblNewLabel_3.setBounds(10, 86, 82, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Has\u0142o:");
		lblNewLabel_4.setBounds(10, 111, 82, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_Has³o = new JTextField();
		textField_Has³o.setBounds(149, 109, 156, 17);
		frame.getContentPane().add(textField_Has³o);
		textField_Has³o.setColumns(10);
		
		textField_Wiek = new JTextField();
		textField_Wiek.setBounds(149, 84, 156, 17);
		frame.getContentPane().add(textField_Wiek);
		textField_Wiek.setColumns(10);
		
		textField_Nazwisko = new JTextField();
		textField_Nazwisko.setBounds(149, 34, 156, 17);
		frame.getContentPane().add(textField_Nazwisko);
		textField_Nazwisko.setColumns(10);
		
		textField_Imie = new JTextField();
		textField_Imie.setBounds(149, 9, 156, 17);
		frame.getContentPane().add(textField_Imie);
		textField_Imie.setColumns(10);
		
		JLabel lblNumerNik = new JLabel("Numer NIK:");
		lblNumerNik.setBounds(10, 136, 64, 14);
		frame.getContentPane().add(lblNumerNik);
		
		textField_numerNIK = new JTextField();
		textField_numerNIK.setBounds(149, 134, 156, 17);
		frame.getContentPane().add(textField_numerNIK);
		textField_numerNIK.setColumns(10);
		
		JLabel lblPesel = new JLabel("Pesel:");
		lblPesel.setBounds(10, 61, 46, 14);
		frame.getContentPane().add(lblPesel);
		
		textField_Pesel = new JTextField();
		textField_Pesel.setBounds(149, 59, 156, 17);
		frame.getContentPane().add(textField_Pesel);
		textField_Pesel.setColumns(10);
		
	
		JButton btnZarejestruj_1 = new JButton("Zarejestruj");
		btnZarejestruj_1.setVisible(false);
		
		
		
		
		JButton btnZarejestruj = new JButton("Przejdz dalej");
		btnZarejestruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
				String imie = null;
				String nazwisko = null;
				String w = null;
				Integer wiek = null;
				String haslo = null;
				String nrNIK = null;
				Integer numerNIK = null;
				String psl = null;
				String pesel = null;
				try {
					imie = textField_Imie.getText();
					nazwisko = textField_Nazwisko.getText();
					w = textField_Wiek.getText();
					wiek = Integer.parseInt(w);
					haslo = textField_Has³o.getText();
					nrNIK = textField_numerNIK.getText();
					numerNIK = Integer.parseInt(nrNIK);
					psl = textField_Pesel.getText();
					pesel = textField_Pesel.getText();
					
					labelnumerRachunku.setVisible(false);
					
					btnGeneruj.setVisible(false);
					btnZarejestruj.setVisible(false);
					btnZarejestruj_1.setVisible(true);
					
					lblNewLabel.setVisible(false);
					lblNewLabel_1.setVisible(false);
					lblNewLabel_3.setVisible(false);
					lblNewLabel_4.setVisible(false);
					lblNumerNik.setVisible(false);
					lblPesel.setVisible(false);
					
					textField_Has³o.setVisible(false);
					textField_Imie.setVisible(false);
					textField_Nazwisko.setVisible(false);
					textField_numerNIK.setVisible(false);
					textField_Pesel.setVisible(false);
					textField_Wiek.setVisible(false);
					lblNumerRachunku.setVisible(true);
					
					lblNazwaRachunku.setVisible(true);
					textField_NazwaRachunku.setVisible(true);
					lblTypRachunku.setVisible(true);
					textField_TypRachunku.setVisible(true);
					lblSaldo.setVisible(true);				
					textField_Saldo.setVisible(true);
					labelnumerRachunku.setVisible(true);				
					btnGeneruj.setVisible(true);
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Nale¿y wype³niæ pola aby przejœæ dalej!", "B³¹d",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnZarejestruj.setBounds(20, 179, 112, 23);
		frame.getContentPane().add(btnZarejestruj);
		
		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnAnuluj.setBounds(142, 179, 115, 23);
		frame.getContentPane().add(btnAnuluj);
		
		
		
		
		btnZarejestruj_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String imie = null;
				String nazwisko = null;
				String w = null;
				Integer wiek = null;
				String haslo = null;
				String nrNIK = null;
				Integer numerNIK = null;
				String psl = null;
				String pesel = null;
				ConnectionDBC con;
			try {
				
				imie = textField_Imie.getText();
				nazwisko = textField_Nazwisko.getText();
				w = textField_Wiek.getText();
				wiek = Integer.parseInt(w);
				haslo = textField_Has³o.getText();
				nrNIK = textField_numerNIK.getText();
				numerNIK = Integer.parseInt(nrNIK);
				psl = textField_Pesel.getText();
				pesel = textField_Pesel.getText();
				labelnumerRachunku.getText();
				textField_NazwaRachunku.getText();
				textField_TypRachunku.getText();
				Float.parseFloat(textField_Saldo.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Coœ posz³o nie tak!!\nNie uda³o siê zarejestrowaæ klienta!!!","B³¹d",JOptionPane.ERROR_MESSAGE);
			}
				
			
				
				try {
					con = new ConnectionDBC();
					 String sSQl = "Insert into `klient` (numerNIK,haslo,imie,nazwisko,wiek,pesel)" + " values (?,?,?,?,?,?)";
					 String s1SQl = "Insert into `konto` (numerRachunku,nazwaRachunku,typ,saldo,k_numerNIK)" + " values (?,?,?,?,?)";
					 PreparedStatement ps = con.getMyConn().prepareStatement(sSQl);
					 PreparedStatement ps1 = con.getMyConn().prepareStatement(s1SQl);
					//java.sql.PreparedStatement ps = con.getMyConn().prepareStatement("Insert into klient (numerNIK,has³o,imie,nazwisko,wiek) values(?,?,?,?,?)");
					ps.setInt(1,numerNIK);
					ps.setString(2,haslo);
					ps.setString(3,imie);
					ps.setString(4,nazwisko);
					ps.setInt(5,wiek);
					ps.setString(6, pesel);
					
					ps1.setString(1, labelnumerRachunku.getText());
					ps1.setString(2, textField_NazwaRachunku.getText());
					ps1.setString(3, textField_TypRachunku.getText());
					ps1.setFloat(4, Float.parseFloat(textField_Saldo.getText()));
					ps1.setInt(5, numerNIK);
					
					
					
					
					ps.execute();
					ps1.execute();
					JOptionPane.showMessageDialog(null,"Uda³o siê zarejestrowaæ nowego klienta");
					//int n = ps.executeUpdate();
					//System.out.println(n);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnZarejestruj_1.setBounds(20, 179, 112, 23);
		frame.getContentPane().add(btnZarejestruj_1);
		
		
		
		
		
		
	}
}
