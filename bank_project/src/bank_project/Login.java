package bank_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import dbc_connection.ConnectionDBC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
	private JTextField textField_Login;
	private JPasswordField textField_Haslo;
	private String name;
	private String surname;
	/**
	 * Launch the application.
	 */
	public static void LoginFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Logowanie");
		frame.setResizable(false);
		frame.setBounds(100, 100, 261, 151);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField_Login = new JTextField();
		textField_Login.setBounds(51, 12, 182, 20);
		frame.getContentPane().add(textField_Login);
		textField_Login.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login:");
		lblNewLabel.setBounds(10, 14, 78, 17);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Has\u0142o:");
		lblNewLabel_1.setBounds(10, 45, 78, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnAnuluj.setBounds(144, 74, 89, 30);
		frame.getContentPane().add(btnAnuluj);
		
		JButton btnLogin = new JButton("Zaloguj");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value1 = Integer.parseInt(textField_Login.getText());
				String numerNIK = textField_Login.getText();
				String value2 = textField_Haslo.getText();
				int user1 = 0;
				String pass1 = "";
				int user2 = 0;
				String pass2 = "";
				int user3 = 0;
				String pass3 = "";
				String name1 = "";
				//System.out.println(value2);
				try {
					ConnectionDBC con = new ConnectionDBC();
					Statement myStmt = con.getMyConn().createStatement();
					ResultSet myRs = myStmt.executeQuery("SELECT * FROM bankier where bankier_id = " + value1);
					Statement myStmt1 = con.getMyConn().createStatement();
					ResultSet myRs1 = myStmt1.executeQuery("select * from klient where numerNIK = " + value1);
					Statement myStmt2 = con.getMyConn().createStatement();
					ResultSet myRs2 = myStmt2.executeQuery("select * from kierownik where kierownik_id = " + value1);
					while (myRs.next()) {
						user1 = myRs.getInt("bankier_id");
						pass1 = myRs.getString("haslo");
						name1 = myRs.getString("imie");
						name = myRs.getString("imie");
						surname = myRs.getString("nazwisko");
						}
					while (myRs1.next()) {
						user2 = myRs1.getInt("numerNIK");
						pass2 = myRs1.getString("haslo");
						name = myRs1.getString("imie");
						surname = myRs1.getString("nazwisko");
						}
					while (myRs2.next()) {
						user3 = myRs2.getInt("kierownik_id");
						pass3 = myRs2.getString("haslo");
						name = myRs2.getString("imie");
						surname = myRs2.getString("nazwisko");
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(value1 == user2 && value2.equals(pass2)) {
					JOptionPane.showMessageDialog(null,"Zosta³eœ zalogowany poprawnie!");
					ClientFrame klient = new ClientFrame(numerNIK);
					klient.clientFrame();
					frame.dispose();
				}
				else if(value1 == user1 && value2.equals(pass1)) {
					JOptionPane.showMessageDialog(null,"Zosta³eœ zalogowany poprawnie!");
					
					BankierFrame bankier = new BankierFrame(numerNIK);
					bankier.bankierFrame();
					frame.dispose();
				}
				else if(value1 == user3 && value2.equals(pass3)) {
					JOptionPane.showMessageDialog(null,"Zosta³eœ zalogowany poprawnie!");
					KierownikFrame kierownik = new KierownikFrame(numerNIK);
					kierownik.kierownikFrame();
					frame.dispose();
				}
				else if(textField_Login.getText().equals("")&&textField_Haslo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Login i has³o nie zosta³y podane!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Logowanie nie powiod³o siê!");
				}
			}
		});
		btnLogin.setBounds(20, 74, 89, 30);
		frame.getContentPane().add(btnLogin);
		
		textField_Haslo = new JPasswordField();
		textField_Haslo.setBounds(51, 43, 182, 20);
		frame.getContentPane().add(textField_Haslo);
	}
	public JTextField getTextField_Login() {
		return textField_Login;
	}
}


