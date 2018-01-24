package bank_project;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

public class Application {

	private MainFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					 UIManager.setLookAndFeel(
					            UIManager.getCrossPlatformLookAndFeelClassName());
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
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame =   new MainFrame();
		frame.setTitle("Aplikacja v1");
		frame.setResizable(false);
		frame.setBounds(100, 100, 271, 260);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNazwa = new JLabel ("Witaj w aplikacji RASS Banku !");
		lblNazwa.setBounds(46, 10, 200, 20);
		frame.getContentPane().add(lblNazwa);
		
		JButton btnKontakt = new JButton("Logowanie");
		btnKontakt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login login = new Login();
				login.LoginFrame();
			}
		});
		btnKontakt.setBounds(46, 80, 170, 30);
		frame.getContentPane().add(btnKontakt);
		
		JButton button = new JButton("Kontakt");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Kontakt do nas!\nNumer telefonu: 987 345 612\nNumer faks: 22 319 44 11\nEmail: jakisBank@o2.pl\n","Kontakt",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		button.setBounds(46, 39, 170, 30);
		frame.getContentPane().add(button);
		
		JButton btnInfo = new JButton("Informacje o banku");
		btnInfo.setBounds(46, 121, 170, 29);
		frame.getContentPane().add(btnInfo);
		
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, " RASS Bank \n Za³o¿yciele: Rados³aw Auguœcik, Sebastian Szko³uda \n Rok za³o¿enia: 2005 \n Nasz bank zosta³ za³o¿ony, aby zapewniæ pañstwu jak najlepsze warunki i bezpieczeñstwo transakcji.\n Mo¿emy siê poszczyciæ nagrodami konsumenckimi 'Bank dla klienta' w latach 2008, 2012, 2015.\n Naszych klientów obdarzamy opiek¹ i trosk¹, co te¿ wp³ywa na to, ¿e tak wielu klientów obdarza nas zaufaniem.","Informacja o banku",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton btnWyjdz = new JButton("Wyjœcie z aplikacji");
		btnWyjdz.setBounds(46, 160, 170, 30);
		frame.getContentPane().add(btnWyjdz);
		
		btnWyjdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
		            int answer = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz zamkn¹æ aplikacjê ?", "Wyjœcie z aplikacji", JOptionPane.YES_NO_OPTION);
		           if (answer == JOptionPane.YES_OPTION)
		           {
		                 System.exit(0);
		           }
		        	}catch (Exception ex) {
		        		ex.printStackTrace();
		        	}
		 
			}
		});
	}
}
